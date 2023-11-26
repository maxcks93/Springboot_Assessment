package com.assessment.todolist.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity // represent a table in database
public class Task {

    @Id // generate primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Hibernate relies on an auto-incremented database column to generate the primary key
    private Long id;
    @NotBlank(message = "Task cannot be blank.")
    private String task;
    @NotNull(message = "Please indicate if the task is completed or not.")
    private Boolean completed;

    public Task(){

    }

    public Task(String task, Boolean completed){
        this.task = task;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task = task;
    }
    public Boolean isCompleted() {
        return completed;
    }
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
