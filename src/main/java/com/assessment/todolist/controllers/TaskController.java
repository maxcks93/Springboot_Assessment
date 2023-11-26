package com.assessment.todolist.controllers;

import com.assessment.todolist.exception.ResourceNotFoundException;
import com.assessment.todolist.models.Task;
import com.assessment.todolist.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@CrossOrigin("http://localhost:8080") //allows requests from http://localhost:8080.
public class TaskController {

    @Autowired
    private TaskService taskService;
    @GetMapping("/")
    public ResponseEntity<Object> getAllTasks() {

        Collection<Task> result = taskService.getAllTask();

        if(!result.isEmpty()){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{

            return new ResponseEntity<>("No Product Available.", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getAllCompletedTasks() {
        List<Task> result = taskService.findAllCompletedTask();

        if(result.isEmpty()){
            throw new ResourceNotFoundException("No completed task is found.");
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/incomplete")
    public ResponseEntity<List<Task>> getAllIncompleteTasks() {
        List<Task> result = taskService.findAllInCompleteTask();

        if(result.isEmpty()){
            throw new ResourceNotFoundException("No incomplete task is found.");
        }
        return ResponseEntity.ok(result);
    }
    @PostMapping("/")
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        return ResponseEntity.ok(taskService.createNewTask(task));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable Long id, @Valid @RequestBody Task task) {
        task.setId(id);
        Task result = taskService.updateTask(id, task).orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " +id));
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAllTasks(@PathVariable Long id) {
        Task result = taskService.findTaskById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " +id));
        taskService.deleteTask(result);
        return ResponseEntity.ok("The task is deleted successfully");
    }
}
