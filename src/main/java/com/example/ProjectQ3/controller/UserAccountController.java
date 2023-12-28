package com.example.ProjectQ3.controller;

import com.example.ProjectQ3.models.User;
import com.example.ProjectQ3.models.UserAccount;
import com.example.ProjectQ3.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<UserAccount> userAccounts(@PathVariable Long userId){
        List<UserAccount> userAccounts = userAccountRepository.findByUserId(userId);
        return userAccounts;
    }

}
