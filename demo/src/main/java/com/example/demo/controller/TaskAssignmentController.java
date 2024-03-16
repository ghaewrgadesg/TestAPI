package com.example.demo.controller;

import com.example.demo.entity.ProjectAssignment;
import com.example.demo.entity.TaskAssignment;
import com.example.demo.service.TaskAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/taskAssignment")
public class TaskAssignmentController {
    private final TaskAssignmentService taskAssignmentService;
    @Autowired
    public TaskAssignmentController(TaskAssignmentService taskAssignmentService) {
        this.taskAssignmentService = taskAssignmentService;
    }

    @GetMapping
    public List<TaskAssignment> getTaskAssignments() {
        return taskAssignmentService.getTaskAssignments();
    }
    //expected body
// {"taskName": "task name",
//  "memberEmail": "email",
//  "projectName": "project name"}
    @PostMapping
    public void addNewProjectAssignment(@RequestBody TaskAssignment taskAssignment){
        taskAssignmentService.addNewTaskAssignment(taskAssignment);
    }

    @DeleteMapping(path = "{memberEmail}")
    public void deleteProject(@PathVariable("memberEmail") String memberEmail,
                              @RequestParam("taskName") String taskName,
                              @RequestParam("projectName") String projectName){
        taskAssignmentService.deleteTaskAssignment(memberEmail,taskName, projectName);
    }
}