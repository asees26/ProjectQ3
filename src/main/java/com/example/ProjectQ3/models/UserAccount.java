package com.example.ProjectQ3.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "userAccount")
public class UserAccount {
@Id
private Long Id;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    private String  userId;

    private Long accountNumber;

    private Long Balance;



    public Long getAccountNumber() {
        return accountNumber;
    }

    public Long getBalance() {
        return Balance;
    }

    public String getAccountType() {
        return accountType;
    }



    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(Long balance) {
        Balance = balance;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    private String accountType;

    private Date creationTime;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
