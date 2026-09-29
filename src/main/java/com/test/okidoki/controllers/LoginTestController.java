package com.test.okidoki.controllers;


import com.test.okidoki.entities.Demo;
import com.test.okidoki.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("loginTest")
public class LoginTestController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/demoToken")
    ResponseEntity<Map<String, String>> demoTest(@Valid @RequestBody Demo demo) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Okidokis rulean");
        response.put("token", demo.getP());

        String token = jwtUtil.generateToken(demo.getUsername(), demo.getRoles());
        List<String> roles = jwtUtil.getRolesFromJwt(token);
        String username = jwtUtil.getUserNameFromJwt(token);
        String passEncoded = jwtUtil.encodePassword(demo.getPassword());

        response.put("token", token);
        response.put("username", username);
        response.put("roles", String.join("roles", roles));
        response.put("passEncoded", passEncoded);


        return ResponseEntity.ok(response);
    }





}
