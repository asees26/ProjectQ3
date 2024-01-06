package com.example.ProjectQ3.models;

import java.util.List;

public class UserDTO {
    private String userName;
    private String email;
    private Long userId;
    private Long accountNumber;
    private String accountType;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserAccounts(List<UserAccountDTO> userAccounts) {
        this.userAccounts = userAccounts;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public List<UserAccountDTO> getUserAccounts() {
        return userAccounts;
    }

    private String address;
    private String name;
    private List<UserAccountDTO> userAccounts;

}
