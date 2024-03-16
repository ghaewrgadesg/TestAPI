package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskAssignmentService {
    private final TaskAssignmentRepository taskAssignmentRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    @Autowired
    public TaskAssignmentService(TaskAssignmentRepository taskAssignmentRepository,
                                 ProjectRepository projectRepository,
                                 UserRepository userRepository,
                                 TaskRepository taskRepository) {
        this.taskAssignmentRepository = taskAssignmentRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public List<TaskAssignment> getTaskAssignments() {
        return taskAssignmentRepository.findAll();
    }
    @Transactional
    public void addNewTaskAssignment(TaskAssignment taskAssignment){

        Optional<TaskAssignment> taskAssignmentOptional =
                taskAssignmentRepository.findTaskAssignmentByEmailAndName(taskAssignment.getMemberEmail(),
                        taskAssignment.getTaskName(),
                        taskAssignment.getProjectName());
        if (taskAssignmentOptional.isPresent()){
            throw new IllegalStateException("A member is already assigned to this task in this project");
        }
        Optional<Project> projectOptional = projectRepository.findProjectByName(taskAssignment.getProjectName());
        if (!projectOptional.isPresent()){
            throw new IllegalStateException("A project with the name '" + taskAssignment.getProjectName() +  "' does not exist");
        }
        Project project = projectOptional.get();
        Optional<User> userOptional = userRepository.findUserByEmail(taskAssignment.getMemberEmail());
        if (!userOptional.isPresent()){
            throw new IllegalStateException("A member with the email '" + taskAssignment.getMemberEmail() + "' does not exist");
        }
        User user = userOptional.get();
        Optional<Task> taskOptional = taskRepository.findTaskByNameAndProject(taskAssignment.getTaskName(),
                taskAssignment.getProjectName());
        if (!taskOptional.isPresent()){
            throw new IllegalStateException("Task '" + taskAssignment.getTaskName() + "' does not exist");
        }
        Task task = taskOptional.get();
        taskAssignment.setTask(task);
        taskAssignment.setProject(project);
        taskAssignment.setUser(user);
        taskAssignmentRepository.save(taskAssignment);
    }

    @Transactional
    public void deleteTaskAssignment(String memberEmail,String taskName, String projectName) {
        Optional<TaskAssignment> taskAssignmentOptional =
                taskAssignmentRepository.findTaskAssignmentByEmailAndName(
                        memberEmail,taskName, projectName);
        if (!taskAssignmentOptional.isPresent()){
            throw new IllegalStateException("Either member or task for this project doesn't exist");
        }
        taskAssignmentRepository.deleteUserByNameAndTask(memberEmail,taskName, projectName);
    }
}
