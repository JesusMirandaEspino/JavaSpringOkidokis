package com.test.okidoki.controllers;


import com.test.okidoki.entities.User;
import com.test.okidoki.security.JwtUtil;
import com.test.okidoki.services.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/all")
    public ResponseEntity<Map< String, Object >>
    getUsers() {
        Map<String, Object> response = new HashMap<>();

        List<User> userList = userService.findAll();

        response.put("message", "Okidokis rulean");
        response.put("Users", userList);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>>  createAccount(@Valid @RequestBody User user) {
        Map<String, Object> response = new HashMap<>();

        String passwordEncoded = jwtUtil.encodePassword(user.getPassword());
        user.setPassword(passwordEncoded);

        Optional<User> userSave = userService.save(user);

        if (userSave.isEmpty())
        {
            response.put("message", "Error creating user");
            return ResponseEntity.status(400).body(response);
        }else{
            response.put("message", "Okidokis rulean");
            response.put("User", userSave);
            return ResponseEntity.ok(response);
        }


    }





}
