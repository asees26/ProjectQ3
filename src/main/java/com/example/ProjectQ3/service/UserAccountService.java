package com.example.ProjectQ3.service;

import com.example.ProjectQ3.models.UserAccount;
import com.example.ProjectQ3.repository.UserAccountRepository;
import java.util.Optional;

public class UserAccountService {

    private UserAccountRepository userAccountRepository;
    public Optional<UserAccount> userAccountList(Long Id){
     return userAccountRepository.findByUserId(Id);
    }
}
