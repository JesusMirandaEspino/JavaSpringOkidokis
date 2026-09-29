package com.test.okidoki.services;

import com.test.okidoki.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> save(User user);
    void deleteById(Long id);
    List<User> findAll();
}
