package com.example.demo.config;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Optional;
@Configuration
public class TaskAssignmentConfig {
    @Bean
    @Order(5)
    CommandLineRunner taskAssignmentCommandLineRunner(TaskAssignmentRepository repository,
                                                      UserRepository userRepository,
                                                      TaskRepository taskRepository,
                                                      ProjectRepository projectRepository){
        return args -> {
            //Making sure that the member is being assigned to an existing project
            Optional<User> userOptional = userRepository.findUserByEmail("testman@gmail.com");
            if (!userOptional.isPresent()){
                throw new IllegalStateException("email doesn't exist");
            }
            User user = userOptional.get();
//            check if task exist
            Optional<Task> taskOptional =
                    taskRepository.findTaskByNameAndProject("Test Task 1", "Test Project");
            if (!taskOptional.isPresent()) {
                throw new IllegalStateException("task doesn't exist");
            }
            Task task = taskOptional.get();
//            check if project exist
            Optional<Project> projectOptional = projectRepository.findProjectByName("Test Project");
            if (!projectOptional.isPresent()) {
                throw new IllegalStateException("project doesn't exist");
            }
            Project project = projectOptional.get();
//          String taskName, String memberEmail, String projectName, Task task, User user, Project project
            TaskAssignment Test = new TaskAssignment(task.getName(),
                    user.getEmail(),
                    project.getName(),
                    task,
                    user,
                    project);
            repository.saveAll(
                    List.of(Test)
            );
        };
    }
}
