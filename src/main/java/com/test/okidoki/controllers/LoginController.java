package com.test.okidoki.controllers;


import com.test.okidoki.entities.Demo;
import com.test.okidoki.entities.LoginUser;
import com.test.okidoki.entities.User;
import com.test.okidoki.entities.payloads.UserDetails;
import com.test.okidoki.security.AuthServices;
import com.test.okidoki.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private AuthServices authServices;

    @PostMapping("/login")
    public ResponseEntity<Map< String, Object >>  login(@Valid @RequestBody LoginUser loginUser) {
        Map<String, Object> response = new HashMap<>();
         UserDetails userDetails = authServices.Authenticate(loginUser.getUserName(), loginUser.getPassword(), loginUser.getEmail());
        if(userDetails.getExist()) {
            response.put("message", "Okidokis rulean");
            response.put("user", userDetails.getUser());
            ResponseCookie cookie = ResponseCookie.from("jwt", userDetails.getToken())
                    .httpOnly(true)
                    .secure(true)
                    .sameSite("Strict")
                    .path("/")
                    .maxAge(3600)
                    .build();


            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body(response);

        }else{
            response.put("message", "User not found or incorrect password, email or username");
            return ResponseEntity.status(400).body(response);
        }



    }




}
