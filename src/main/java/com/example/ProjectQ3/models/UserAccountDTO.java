package com.example.ProjectQ3.models;

import java.time.LocalDateTime;
import java.util.Date;

public class UserAccountDTO {
    private Long accountNumber;

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    private Long balance;
    private String accountType;

    public Long getAccountNumber() {
        return accountNumber;
    }

    public Long getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    private LocalDateTime creationTime;

    // Constructors, getters, setters, and other methods
}
