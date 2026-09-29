package com.test.okidoki.repositories;

import com.test.okidoki.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    public Optional<User> findByEmail(String email);
    public User findByUsername(String username);
}
