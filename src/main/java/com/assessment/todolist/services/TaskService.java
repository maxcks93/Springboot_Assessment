package com.assessment.todolist.services;

import com.assessment.todolist.exception.ResourceNotFoundException;
import com.assessment.todolist.models.Task;
import com.assessment.todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service // semantic version of @Components that represent class in service layer
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task createNewTask(Task task) {
        return taskRepository.save(task);
    }

    public Collection<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public Optional<Task> findTaskById(Long id){
        Optional<Task> result =taskRepository.findById(id);

        try{
            return Optional.of(result.get());
        }catch(Exception e) {
            return result;
        }
    }

    public List<Task> findAllCompletedTask() {
        return taskRepository.findByCompletedTrue();
    }

    public List<Task> findAllInCompleteTask() {
        return taskRepository.findByCompletedFalse();
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    public Optional<Task> updateTask(Long id, Task task){
        Optional<Task> result =taskRepository.findById(id);

        try{
            Task temp = result.get();
            temp.setTask(task.getTask());
            temp.setCompleted(task.isCompleted());
            return Optional.of(taskRepository.save(temp));
        }catch(Exception e) {
            return result;
        }
    }
}