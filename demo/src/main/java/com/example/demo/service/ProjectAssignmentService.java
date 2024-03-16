package com.example.demo.service;

import com.example.demo.entity.Project;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.entity.ProjectAssignment;
import com.example.demo.repository.ProjectAssignmentRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectAssignmentService {
    private final ProjectAssignmentRepository projectAssignmentRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    @Autowired
    public ProjectAssignmentService(ProjectAssignmentRepository projectAssignmentRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectAssignmentRepository = projectAssignmentRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public List<ProjectAssignment> getProjectAssignments() {
        return projectAssignmentRepository.findAll();
    }

    @Transactional
    public void addNewProjectAssignment(ProjectAssignment projectAssignment){
        Optional<Project> projectOptional = projectRepository.findProjectByName(projectAssignment.getProjectName());
        if (!projectOptional.isPresent()){
            throw new IllegalStateException("A project with the name " + projectAssignment.getProjectName() +  " does not exist");
        }
        Project project = projectOptional.get();
        Optional<User> userOptional = userRepository.findUserByEmail(projectAssignment.getMemberEmail());
        if (!userOptional.isPresent()){
            throw new IllegalStateException("A member with the email " + projectAssignment.getMemberEmail() +  " is already assigned to this project");
        }
        User user = userOptional.get();
        projectAssignment.setProject(project);
        projectAssignment.setMember(user);
        projectAssignmentRepository.save(projectAssignment);
    }
    @Transactional
    public void deleteProjectAssignment(String memberEmail, String projectName) {
        Optional<ProjectAssignment> projectAssignmentOptional =
                projectAssignmentRepository.findProjectAssignmentByEmailAndName(memberEmail, projectName);
        if (!projectAssignmentOptional.isPresent()){
            throw new IllegalStateException("Either member or project doesn't exist");
        }
        projectAssignmentRepository.deleteTaskByNameAndProject(memberEmail, projectName);
    }
}
