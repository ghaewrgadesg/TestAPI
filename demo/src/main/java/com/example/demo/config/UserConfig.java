package com.example.demo.config;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    @Order(1)
    CommandLineRunner userCommandLineRunner(UserRepository repository){
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
            User Horus = new User("HorusPlsComeHome@gmail.com",
                    "Horus Lightwing",
                    "LightWingsShore",
                    LocalDate.of(1000, Month.MARCH, 15)
            );
            repository.saveAll(
                    List.of(testMan, Gorlock, Horus)
            );
        };
    }
}
