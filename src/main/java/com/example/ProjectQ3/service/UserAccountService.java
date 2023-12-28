package com.example.ProjectQ3.service;

import com.example.ProjectQ3.models.UserAccount;
import com.example.ProjectQ3.repository.UserAccountRepository;
import java.util.List;

public class UserAccountService {

    private UserAccountRepository userAccountRepository;
    public List<UserAccount> userAccountList(Long Id){
     return userAccountRepository.findByUserId(Id);
    }
}
