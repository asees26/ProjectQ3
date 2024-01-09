package com.example.ProjectQ3.controller;
import com.example.ProjectQ3.models.User;
import com.example.ProjectQ3.models.UserAccount;
import com.example.ProjectQ3.models.UserAccountDTO;
import com.example.ProjectQ3.models.UserDTO;
import com.example.ProjectQ3.repository.UserAccountRepository;
import com.example.ProjectQ3.repository.UserRepository;
import com.example.ProjectQ3.service.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public List<User> createUser(@RequestBody List<User> user) {
        return userRepository.saveAll(user);
    }

    @GetMapping("/{userId}")
    public Optional<User> findByUserID(@PathVariable Long userId) {
        Optional<User> user = userRepository.findByUserId(userId);
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
                Optional<User> existingUser = userRepository.findByUserId(userDTO.getUserId());
                if (existingUser.isPresent()) {
                    throw new UserAlreadyExistsException("User with ID " + userDTO.getUserId() + " already exists");
                } else {
                    User user = new User();
                    user.setUserName(userDTO.getUserName());
                    user.setEmail(userDTO.getEmail());
                    user.setAddress(userDTO.getAddress());
                    user.setName(userDTO.getName());
                    user.setUserId(userDTO.getUserId());

                    List<UserAccount> userAccounts = new ArrayList<>();
                    for (UserAccountDTO accountDTO : userDTO.getUserAccounts()) {
                        UserAccount account = convertToUserAccount(accountDTO, userDTO);
                        userAccounts.add(account);
                    }

                    user.setUserAccounts(userAccounts);
                    userRepository.save(user);
                }
            }
            return new ResponseEntity<>("Users and UserAccounts created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating Users and UserAccounts.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/addUserAccount")
    public ResponseEntity<String> updateUserAccount(@RequestBody List<UserDTO> userDTOList) {
        try {
            for (UserDTO userDTO : userDTOList) {
                Optional<User> optionalUser = userRepository.findById(userDTO.getUserId());
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    user.setUserName(userDTO.getUserName());
                    user.setEmail(userDTO.getEmail());
                    user.setAddress(userDTO.getAddress());
                    user.setName(userDTO.getName());
                    List<UserAccount> existingUserAccounts = user.getUserAccounts();
                    UserAccountDTO newAccountDTO = userDTO.getUserAccounts().get(0);
                    if (existingUserAccounts.stream()
                            .anyMatch(userAccount -> userAccount.getAccountNumber().equals(newAccountDTO.getAccountNumber()))) {
                        throw new RuntimeException("Account number already exists: " + newAccountDTO.getAccountNumber());
                    }
                    List<UserAccount> newUserAccounts = new ArrayList<>();
                    for (UserAccountDTO accountDTO : userDTO.getUserAccounts()) {
                        UserAccount account = convertToUserAccount(accountDTO, userDTO);
                        newUserAccounts.add(account);
                    }
                    existingUserAccounts.addAll(newUserAccounts);
                    user.setUserAccounts(existingUserAccounts);
                    userRepository.save(user);
                }
            }
            return new ResponseEntity<>("Users and UserAccounts created/updated successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating/updating Users and UserAccounts.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
