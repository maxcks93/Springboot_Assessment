package com.assessment.todolist.repositories;

import com.assessment.todolist.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // semantic version of @Components that represent class in repository layer
public interface TaskRepository extends JpaRepository<Task, Long> {
    public Task findByTask(String task);
    public List<Task> findByCompletedTrue();
    public List<Task> findByCompletedFalse();
    public List<Task> findAll();
    public Task getById(Long id);
}
