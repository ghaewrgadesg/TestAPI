package com.example.demo.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@IdClass(TaskId.class)
@Entity
@Table(name = "Tasks")
public class Task {

    @Id
    private String name;

    @Id
    private String projectName;

    private LocalDate startDate;

    private LocalDate endDate;

    private String status;


    @ManyToOne
    private Project project;

    public Task(String name, String projectName, LocalDate startDate, LocalDate endDate, String status, Project project) {
        this.name = name;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.project = project;
    }

    public Task(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
