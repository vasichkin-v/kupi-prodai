package com.example.kupiprodai.services;

import com.example.kupiprodai.enums.Role;
import com.example.kupiprodai.models.User;
import com.example.kupiprodai.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
//@AllArgsConstructor // для всех полей
@RequiredArgsConstructor // Конструктор только для final полей!
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        if (user == null || user.getEmail() == null) {
            log.debug("User is null or have null email: [{}]", user);
            throw new RuntimeException("User is null or have null email");
        }

        String email = user.getEmail();
        User byEmail = userRepository.findByEmail(email);

        if (byEmail != null) {
            log.debug("User with this email address is already registered: [{}]", byEmail);
            throw new RuntimeException("User with this email address is already registered");
        }

        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.USER);
        User savedUser = userRepository.save(user);
        log.info("User saved: [{}]", savedUser);
        return true;
    }
}
