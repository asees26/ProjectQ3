package com.example.ProjectQ3.service;

import com.example.ProjectQ3.models.User;
import com.example.ProjectQ3.repository.UserRepository;
import java.util.List;

public class UserService {

    private UserRepository userRepository;
    public List<User> findByUserID(Long userId) {
        return userRepository.findByUserId(userId);
    }
}
