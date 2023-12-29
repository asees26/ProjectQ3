package com.example.ProjectQ3.controller;

import com.example.ProjectQ3.models.User;
import com.example.ProjectQ3.models.UserAccount;
import com.example.ProjectQ3.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Optional<UserAccount> userAccounts(@PathVariable String userId){
        Optional<UserAccount> userAccounts = userAccountRepository.findByUserId(userId);
        return userAccounts;
    }

    @PutMapping("/{userId}")
    public Optional<UserAccount> updateUserAccount(@PathVariable String userId,@RequestBody UserAccount userAccount){
        Optional<UserAccount> existingUserAccount = userAccountRepository.findByUserId(userId);
       if(existingUserAccount.isPresent()){
           UserAccount userAccount1 = existingUserAccount.get();
           userAccount1.setBalance(userAccount.getBalance());
           userAccountRepository.save(userAccount1);
       }
       return Optional.ofNullable(userAccount);
    }

}
