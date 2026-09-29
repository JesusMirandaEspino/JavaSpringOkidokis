package com.test.okidoki.repositories;

import com.test.okidoki.entities.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TaskRepository extends CrudRepository<Task, Long> {

    public Task findByName(String name);
    public Task findByStatus(String status);
}
