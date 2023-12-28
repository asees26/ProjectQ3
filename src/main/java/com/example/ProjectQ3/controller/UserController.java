package com.example.ProjectQ3.controller;
import com.example.ProjectQ3.models.User;
import com.example.ProjectQ3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public List<User> findByUserID(@PathVariable String userId) {
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
        if(user.isPresent()){
           userRepository.deleteById(id);
        }
        else {
            throw new UserNotFoundException();
        }
        return null;
    }
}
