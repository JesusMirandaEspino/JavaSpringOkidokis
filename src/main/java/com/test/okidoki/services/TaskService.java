package com.test.okidoki.services;

import com.test.okidoki.entities.Task;
import com.test.okidoki.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements ITaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task findByName(String nombre) {
        return taskRepository.findByName(nombre);
    }

    @Override
    public Task findByStatus(String status) {
        return taskRepository.findByStatus(status);
    }


    @Override
    public Optional<Task> save(Task task) {
        return Optional.of(taskRepository.save(task));
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
