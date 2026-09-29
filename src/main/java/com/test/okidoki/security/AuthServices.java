package com.test.okidoki.security;


import com.test.okidoki.entities.User;
import com.test.okidoki.entities.payloads.UserDetails;
import com.test.okidoki.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServices {

    @Autowired
    IUserService userService;


    @Autowired
    private JwtUtil jwtUtil;


    public UserDetails Authenticate(String username, String password, String email) {

        UserDetails userDetails = new UserDetails();

        userDetails.setExist(false);

        userService.findByEmail(email).ifPresent(user -> {

            System.out.println("Usuario encontrado: " + user.getUsername());
            System.out.println("Email: " + user.getEmail());

            if (jwtUtil.matchesPassword(password, user.getPassword()) ) {



                String token = jwtUtil.generateToken(user.getUsername(), user.getRoles());

                userDetails.setUser(user);
                userDetails.setToken(token);
                userDetails.setExist(true);

            } else {
                System.out.println("Contraseña incorrecta para el usuario: " + user.getUsername());
                userDetails.setExist(false);
                userDetails.setToken(null);
                userDetails.setUser(null);

            }
        });



        return userDetails;
    }

}
