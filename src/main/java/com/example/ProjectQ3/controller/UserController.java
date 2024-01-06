package com.example.ProjectQ3.controller;
import com.example.ProjectQ3.models.User;
import com.example.ProjectQ3.models.UserAccount;
import com.example.ProjectQ3.models.UserAccountDTO;
import com.example.ProjectQ3.models.UserDTO;
import com.example.ProjectQ3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public List<User> createUser(@RequestBody List<User> user) {
        return userRepository.saveAll(user);
    }

    @GetMapping("/{userId}")
    public List<User> findByUserID(@PathVariable Long userId) {
        List<User> user = userRepository.findByUserId(userId);
        return user;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser;
    }

    @PutMapping("/{id}")
    public Optional<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setAddress(updatedUser.getAddress());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setUserName(existingUser.getUserName());
            userRepository.save(existingUser);
        } else {
            throw new UserNotFoundException();
        }
        return user;
    }

    @DeleteMapping("/{id}")
    public List<User> deleteUser(@PathVariable Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException();
        }
        return null;
    }

    private UserAccount convertToUserAccount(UserAccountDTO accountDTO,UserDTO userDTO) {
        UserAccount account = new UserAccount();
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setBalance(accountDTO.getBalance());
        account.setAccountType(accountDTO.getAccountType());
        account.setCreationTime(accountDTO.getCreationTime());
        account.setUserId(userDTO.getUserId());
        return account;
    }


    @PostMapping("/create")
    public ResponseEntity<String> createUserAndAccounts(@RequestBody List<UserDTO> userDTOList) {
        try {
            for (UserDTO userDTO : userDTOList) {
                User user = new User();
                user.setUserName(userDTO.getUserName());
                user.setEmail(userDTO.getEmail());
                user.setAddress(userDTO.getAddress());
                user.setName(userDTO.getName());
                user.setUserId(userDTO.getUserId());

                List<UserAccount> userAccounts = new ArrayList<>();
                for (UserAccountDTO accountDTO : userDTO.getUserAccounts()) {
                    UserAccount account = convertToUserAccount(accountDTO,userDTO);
                    account.setUser(user);
                    userAccounts.add(account);
                }

                user.setUserAccounts(userAccounts);
                userRepository.save(user);
            }
            return new ResponseEntity<>("Users and UserAccounts created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating Users and UserAccounts.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
