package com.example.ProjectQ3.repository;

import com.example.ProjectQ3.models.User;
import com.example.ProjectQ3.models.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(Long userId);
    List<User> findByUserName(String userName);

}
