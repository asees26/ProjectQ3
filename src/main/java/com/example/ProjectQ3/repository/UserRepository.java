package com.example.ProjectQ3.repository;

import com.example.ProjectQ3.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserId(String userId);
    List<User> findByUserName(String userName);
}
