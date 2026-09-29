package com.test.okidoki.controllers;



import com.test.okidoki.entities.Task;
import com.test.okidoki.services.ITaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("task")
public class TaksController {

    @Autowired
    ITaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createAccount(@Valid @RequestBody Task task) {
        Map<String, Object> response = new HashMap<>();


        Optional<Task> taskSaved = taskService.save(task);

        if (taskSaved.isEmpty())
        {
            response.put("message", "Error creating user");
            return ResponseEntity.status(400).body(response);
        }else{
            response.put("message", "Okidokis rulean");
            return ResponseEntity.ok(response);
        }


    }

}
