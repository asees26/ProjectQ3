package com.example.ProjectQ3.service;

import com.example.ProjectQ3.models.User;
import com.example.ProjectQ3.models.UserDTO;
import com.example.ProjectQ3.repository.UserRepository;
import java.util.List;
import java.util.Optional;

public class UserService {

    private UserRepository userRepository;
    public Optional<User> findByUserID(Long userId) {
        return userRepository.findByUserId(userId);
    }

    public void createUserAndAccounts(UserDTO userDTO) throws UserAlreadyExistsException {
        Optional<User> existingUser = userRepository.findByUserId(userDTO.getUserId());
        if(existingUser.isPresent()){
            throw new UserAlreadyExistsException("User already present with the given userId");
        }
    }
}
