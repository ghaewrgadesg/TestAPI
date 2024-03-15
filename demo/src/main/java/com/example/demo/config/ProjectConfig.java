package com.example.demo.config;

import com.example.demo.entity.Project;
import com.example.demo.entity.User;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Configuration
public class ProjectConfig {
    @Bean
    @Order(2)
    CommandLineRunner projectCommandLineRunner(ProjectRepository repository, UserRepository userRepository){
        return args -> {
            Optional<User> managerOptional = userRepository.findUserByEmail("Gorlock123@gmail.com");
            if (!managerOptional.isPresent()){
                throw new IllegalStateException("email doesn't exist");
            }
            User manager = managerOptional.get();
            Project Test = new Project("Gorlock123@gmail.com",
                    LocalDate.now(),
                    LocalDate.of(2024, 12, 7),
                    "Test Project",
                    manager);
            repository.saveAll(
                    List.of(Test)
            );
        };
    }
}
