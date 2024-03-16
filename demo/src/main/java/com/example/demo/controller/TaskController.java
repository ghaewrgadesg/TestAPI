package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/task")
public class TaskController {
    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @PostMapping
    public void addNewTask(@RequestBody Task task){
        taskService.addNewTask(task);
    }

    @DeleteMapping(path = "{taskName}")
    public void deleteTask(@PathVariable("taskName") String taskName,
                           @RequestParam("projectName") String projectName){
        taskService.deleteTask(taskName, projectName);
    }

    @PutMapping(path = "{taskName}/{projectName}")
    public void updateUser(@PathVariable("taskName") String taskName,
                           @PathVariable("projectName") String projectName,
                           @RequestParam(required = false) String newName,
                           @RequestParam(required = false) String status,
                           @RequestParam(required = false) LocalDate endDate){

        taskService.updateTask(projectName, taskName, newName, status, endDate);
    }
}
