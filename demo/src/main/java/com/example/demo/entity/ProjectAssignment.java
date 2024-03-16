package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table
public class ProjectAssignment {

    @Id
    private String memberEmail;

    @Id
    private String projectName;

    @ManyToOne
    private User user;

    @ManyToOne
    private Project project;

}
