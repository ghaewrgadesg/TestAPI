package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String email, name, username;
    private LocalDate DOB;
    @Transient
    private Integer age;
    public User(Long id, String email, String name, String username, LocalDate DOB) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.username = username;
        this.DOB = DOB;
    }

    public User(String email, String name, String username, LocalDate DOB) {
        this.email = email;
        this.name = name;
        this.username = username;
        this.DOB = DOB;
    }
    public User() {
    }
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public Integer getAge() {
        return Period.between(this.DOB, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", DOB=" + DOB +
                '}';
    }
}

