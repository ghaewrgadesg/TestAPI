package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@IdClass(ProjectAssignmentId.class)
@Entity
@Table
public class ProjectAssignment {

    @Id
    private String memberEmail;

    @Id
    private String projectName;

    @ManyToOne
    private User member;

    @ManyToOne
    private Project project;

    public ProjectAssignment(String memberEmail, String projectName, User member, Project project) {
        this.memberEmail = memberEmail;
        this.projectName = projectName;
        this.member = member;
        this.project = project;
    }

    public ProjectAssignment(){

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

    public User getMember() {
        return member;
    }

    public void setMember(User member) {
        this.member = member;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
