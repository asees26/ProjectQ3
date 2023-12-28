package com.example.ProjectQ3.repository;
import com.example.ProjectQ3.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    List<UserAccount> findByUserId(Long Id);

}
