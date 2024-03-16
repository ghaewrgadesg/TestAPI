package com.example.demo.entity;

import jakarta.persistence.*;

import java.io.Serializable;


@IdClass(TaskAssignmentId.class)
@Entity
public class TaskAssignment {
    @Id
    @Column(name = "member_task_name")//need to be different from the name column in task due to column mapping problems
    private String taskName;
    @Id
    @Column(name = "member_email")
    private String memberEmail;

    @Id
    @Column(name = "project_name")
    private String projectName;

    @ManyToOne
    private Task task;

    @ManyToOne
    private User user;

    @ManyToOne
    private Project project;

    // Constructors, getters, and setters

    public TaskAssignment(String taskName, String memberEmail, String projectName, Task task, User user, Project project) {
        this.taskName = taskName;
        this.memberEmail = memberEmail;
        this.projectName = projectName;
        this.task = task;
        this.user = user;
        this.project = project;
    }

    public TaskAssignment() {
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
