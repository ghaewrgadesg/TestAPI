package com.example.demo.service;


import com.example.demo.entity.Project;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.TaskRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final static List<String> eligibleStatus = new ArrayList<>(List.of("IN PROGRESS", "FINISHED", "NOT STARTED"));
    @Autowired
    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

//    Body should have the format:
//    {"name": "name",
//     "projectName": "project name",
//     "endDate": "yyyy-mm-dd",
//     "status"}
    @Transactional
    public void addNewTask(Task task) {
        Optional<Project> projectOptional = projectRepository.findProjectByName(task.getProjectName());
        if (!projectOptional.isPresent()){
            throw new IllegalStateException("A project with the name " + task.getProjectName() +  " does not exist");
        }
        if (!eligibleStatus.contains(task.getStatus())){
            throw new IllegalStateException("Wrong Status value ('IN PROGRESS, FINISHED, NOT STARTED')");
        }
        if (task.getEndDate().compareTo(LocalDate.now()) < 0){
            throw new IllegalStateException("End Date can't be earlier than today");
        }
        //        Not entirely sure if adding the project
        Project project = projectOptional.get();
        task.setProject(project);
        task.setStartDate(LocalDate.now());
        taskRepository.save(task);
    }
    @Transactional
    public void deleteTask(String taskName, String projectName) {
        Optional<Project> projectOptional = projectRepository.findProjectByName(projectName);
        if (!projectOptional.isPresent()){
            throw new IllegalStateException("Project doesn't exist");
        }
        Optional<Task> taskOptional = taskRepository.findTaskByNameAndProject(taskName, projectName);
        if (!taskOptional.isPresent()){
            throw new IllegalStateException("Task " + taskName + " for the project " + projectName +" doesn't exist");
        }
        taskRepository.deleteTaskByNameAndProject(taskName, projectName);
    }

    @Transactional
    public void updateTask(String projectName, String taskName, String newName,  String status, LocalDate endDate){
        Optional<Task> taskOptional = taskRepository.findTaskByNameAndProject(taskName, projectName);
        if (!taskOptional.isPresent()){
            throw new IllegalStateException("Task " + taskName + " for the project " + projectName +" doesn't exist");
        }
        Task task = taskOptional.get();
//        this doesn't work because the ID is composed of name and project Name but I'm leaving this here anyway
//        if (newName != null && newName.length() > 0 &&
//                !Objects.equals(task.getName(), newName)){
//            taskOptional = taskRepository.findTaskByNameAndProject(newName, projectName);
//            if (taskOptional.isPresent()){
//                throw new IllegalStateException("name already in used");
//            }
//            task.setName(newName);
//        }

        if (status != null){
            if (!eligibleStatus.contains(status)){
                throw new IllegalStateException("Wrong Status value ('IN PROGRESS, FINISHED, NOT STARTED')");
            }
            task.setStatus(status);
        }


        if (endDate != null &&  endDate.compareTo(LocalDate.now()) > 0 &&
                !Objects.equals(task.getEndDate(), endDate)){
            task.setEndDate(endDate);
        }
    }
}
