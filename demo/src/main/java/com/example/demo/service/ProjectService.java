package com.example.demo.service;

import com.example.demo.entity.Project;
import com.example.demo.entity.User;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    @Autowired
    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

//    The expected format for the body is:
//    {"managerEmail" : "email",
//      "endDate" : "yyyy-mm-dd",
//      "name" : "name"}
    @Transactional
    public void addNewProject(Project project) {
        Optional<Project> projectOptional = projectRepository.findProjectByName(project.getName());
        if (projectOptional.isPresent()){
            throw new IllegalStateException("A project with that name already exists");
        }
//        Not entirely sure if adding a manager here is needed or just manager email is fine
        Optional<User> managerOptional = userRepository.findUserByEmail(project.getManagerEmail());
        if (!managerOptional.isPresent()){
            throw new IllegalStateException("email doesn't exist " + project.getManagerEmail());
        }
        if (project.getEndDate().compareTo(LocalDate.now()) < 0){
            throw new IllegalStateException("End Date can't be earlier than today");
        }
        User manager = managerOptional.get();
        project.setManager(manager);
        project.setStartDate(LocalDate.now());
        projectRepository.save(project);
    }
    public void deleteProject(Long projectId) {
        boolean exists = projectRepository.existsById(projectId);
        if (!exists){
            throw new IllegalStateException("Project with Id: " + projectId + " does not exist");
        }
        projectRepository.deleteById(projectId);
    }

    @Transactional
    public void updateProject(Long projectId, String name, String managerEmail, LocalDate endDate) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalStateException
                ("Project with Id: " + projectId + " does not exist"));

        if (name != null && name.length() > 0 &&
                !Objects.equals(project.getName(), name)){
            Optional<Project> projectOptional = projectRepository.findProjectByName(name);
            if (projectOptional.isPresent()){
                throw new IllegalStateException("name already in used");
            }
            project.setName(name);
        }

        if (managerEmail != null && managerEmail.length() > 0 &&
                !Objects.equals(project.getManagerEmail(), managerEmail)){
            Optional<User> userOptional = userRepository.findUserByEmail(managerEmail);
            if (!userOptional.isPresent()){
                throw new IllegalStateException("email does not exist");
            }
            project.setManagerEmail(managerEmail);
        }

        if (endDate != null &&  endDate.compareTo(LocalDate.now()) > 0 &&
                !Objects.equals(project.getEndDate(), endDate)){
            project.setEndDate(endDate);
        }
    }

}
