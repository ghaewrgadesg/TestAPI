package com.example.demo.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {
            User testMan = new User("testman@gmail.com",
                    "Test Man",
                    "Testman2004",
                    LocalDate.of(2000, Month.JANUARY, 4)
            );
            User Gorlock = new User("Gorlock123@gmail.com",
                    "Gorlock Doorlock",
                    "GorDoor200",
                    LocalDate.of(2001, Month.JUNE, 10)
            );
            repository.saveAll(
                    List.of(testMan, Gorlock)
            );
        };
    }
}
