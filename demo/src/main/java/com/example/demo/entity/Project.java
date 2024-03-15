package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String managerEmail;

    private LocalDate startDate;

    private LocalDate endDate;

    private String name;

    @ManyToOne
    private User manager;

    public Project(String managerEmail, LocalDate startDate, LocalDate endDate, String name, User manager) {
        this.managerEmail = managerEmail;
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.manager = manager;
    }
    public Project(String managerEmail, LocalDate startDate, LocalDate endDate, String name) {
        this.managerEmail = managerEmail;
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
    }
    public Project(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }
}
