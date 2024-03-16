package com.example.demo.config;

import com.example.demo.entity.Project;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Configuration
public class TaskConfig {
    @Bean
    @Order(3)
    CommandLineRunner taskCommandLineRunner(TaskRepository repository, ProjectRepository projectRepository, UserRepository userRepository){
        return args -> {
            Optional<Project> projectOptional = projectRepository.findProjectByName("Test Project");
            if (!projectOptional.isPresent()){
                throw new IllegalStateException("project doesn't exist");
            }
            Project project = projectOptional.get();
//            String name, String projectName, LocalDate startDate, LocalDate endDate, String status, Project project
            Task Test = new Task("Test Task 1",
                    "Test Project",
                    LocalDate.now(),
                    LocalDate.of(2023, 11, 9),
                    "In progress",
                    project);
            repository.saveAll(
                    List.of(Test)
            );
        };
    }
}
