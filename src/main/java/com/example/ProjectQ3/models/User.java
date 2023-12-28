package com.example.ProjectQ3.models;


import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

@Id
@Column(name = "id")
    private Long id;

private String name;

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Long getId() {
        return id;
    }

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    private String email;

    private String address;

    private Long accountNumber;

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    private String accountType;


    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
