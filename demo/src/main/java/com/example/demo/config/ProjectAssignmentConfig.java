package com.example.demo.config;


import com.example.demo.entity.Project;
import com.example.demo.entity.ProjectAssignment;
import com.example.demo.entity.User;
import com.example.demo.repository.ProjectAssignmentRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Configuration
public class ProjectAssignmentConfig {
    @Bean
    @Order(4)
    CommandLineRunner projectAssignmentCommandLineRunner(ProjectAssignmentRepository repository, UserRepository userRepository, ProjectRepository projectRepository){
        return args -> {
            //Making sure that the member is being assigned to an existing project
            Optional<User> memberOptional = userRepository.findUserByEmail("testman@gmail.com");
            if (!memberOptional.isPresent()){
                throw new IllegalStateException("email doesn't exist");
            }
            User member = memberOptional.get();
            Optional<Project> projectOptional = projectRepository.findProjectByName("Test Project");
            if (!projectOptional.isPresent()) {
                throw new IllegalStateException("project doesn't exist");
            }
//          String memberEmail, String projectName, User user, Project project
            Project project = projectOptional.get();
//            I don't believe there's a need to add the manager to this
            if (project.getManagerEmail() == member.getEmail()){
                throw new IllegalStateException("Member is already a manager");
            }
            ProjectAssignment Test = new ProjectAssignment("Gorlock123@gmail.com",
                    "Test Project",
                    member,
                    project);
            repository.saveAll(
                    List.of(Test)
            );
        };
    }
}
