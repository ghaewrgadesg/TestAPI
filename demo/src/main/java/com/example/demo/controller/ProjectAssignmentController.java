package com.example.demo.controller;

import com.example.demo.entity.Project;
import com.example.demo.entity.ProjectAssignment;
import com.example.demo.service.ProjectAssignmentService;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/projectAssignment")
public class ProjectAssignmentController {
    private final ProjectAssignmentService projectAssignmentService;
    @Autowired
    public ProjectAssignmentController(ProjectAssignmentService projectAssignmentService) {
        this.projectAssignmentService = projectAssignmentService;
    }

    @GetMapping
    public List<ProjectAssignment> getProjectAssignments() {
        return projectAssignmentService.getProjectAssignments();
    }

    //    maybe a different post mapping with paths is better but idk
//    expected body is: {"email": email,
//                    "projectName": project}
//    with so little in the body maybe a different post mapping is better still
    @PostMapping
    public void addNewProjectAssignment(@RequestBody ProjectAssignment projectAssignment){
        projectAssignmentService.addNewProjectAssignment(projectAssignment);
    }

    @DeleteMapping(path = "{memberEmail}")
    public void deleteProject(@PathVariable("memberEmail") String memberEmail, @RequestParam("projectName") String projectName){
        projectAssignmentService.deleteProjectAssignment(memberEmail, projectName);
    }

    //I don't think an update is necessary (just delete the old one and add a new one)
}
