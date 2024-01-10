package com.example.ProjectQ3.controller;

import com.example.ProjectQ3.models.User;
import com.example.ProjectQ3.models.UserAccount;
import com.example.ProjectQ3.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/{userAccount}")
public class UserAccountController {
    @Autowired
    UserAccountRepository userAccountRepository;

    @PostMapping
    public List<UserAccount> userAccountList(@RequestBody List<UserAccount> userAccount) {
        return userAccountRepository.saveAll(userAccount);
    }

    @GetMapping("/{userId}")
    public Optional<UserAccount> userAccounts(@PathVariable Long userId){
        Optional<UserAccount> userAccounts = userAccountRepository.findByUserId(userId);
        return userAccounts;
    }

    @GetMapping
    public List<UserAccount> getAllUsersAccount() {
        return userAccountRepository.findAll();
    }


    @PutMapping("/{userId}")
    public ResponseEntity<UserAccount> updateUserAccount(@PathVariable Long userId, @RequestBody UserAccount userAccount){
        Optional<UserAccount> existingUserAccount = userAccountRepository.findByUserId(userId);
       if(existingUserAccount.isPresent()){
           UserAccount userAccount1 = existingUserAccount.get();
           userAccount1.setBalance(userAccount.getBalance());
           userAccountRepository.save(userAccount1);
           return ResponseEntity.ok(userAccount1);
       }
       else {
           return ResponseEntity.notFound().build();
    }
    }

    @DeleteMapping({"/{userId}"})
    public Optional<UserAccount> deleteUserAccount(@PathVariable Long userId){
        Optional<UserAccount> dbUserAccount = userAccountRepository.findByUserId(userId);
        if(dbUserAccount.isPresent()){
            userAccountRepository.deleteById(userId);
        }
        return null;
    }

}
