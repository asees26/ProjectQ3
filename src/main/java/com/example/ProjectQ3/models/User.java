package com.example.ProjectQ3.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user")
public class User {

    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private List<UserAccount> userAccounts;
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_name")
    private String userName;
    private String email;
    private String address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(List<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
