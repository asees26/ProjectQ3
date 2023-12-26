package com.example.ProjectQ3.controller;

import com.example.ProjectQ3.models.User;
import com.example.ProjectQ3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/yourEntity")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User createEntity(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/{userId}")
    public List<User> findByUserID(String userId) {
        List<User> user = userRepository.findByUserId(userId);
        return user;
    }


}
