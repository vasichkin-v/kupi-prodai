package com.example.kupiprodai.repositories;

import com.example.kupiprodai.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
      public User findByEmail(String email);
}
