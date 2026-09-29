package com.test.okidoki.services;

import com.test.okidoki.entities.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskService {
    Task findByName(String nombre);
    Task findByStatus(String status);
    Optional<Task> save(Task task);
    void deleteById(Long id);
}
