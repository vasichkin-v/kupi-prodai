package com.example.kupiprodai.services;

import com.example.kupiprodai.enums.Role;
import com.example.kupiprodai.models.User;
import com.example.kupiprodai.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@AllArgsConstructor // Для всех полей
@RequiredArgsConstructor // Конструктор только для полей помеченных как final
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        if (user == null || user.getEmail() == null) {
            log.debug("User is null or have null email: [{}]", user);
            throw new RuntimeException("User is null or have null email");
        }
        User byEmail = userRepository.findByEmail(user.getEmail());
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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getById(Long userId) {
        Optional<User> byId = userRepository.findById(userId);
        if(byId.isEmpty()) {
            log.debug("Not found user with id: {}", userId);
            return new User();
        }
        return byId.get();
    }

    public void userBаnById(Long userId) {
        User byId = getById(userId);
        if(byId.getId() != null){
            byId.setActive(!byId.isActive());
            userRepository.save(byId);
            log.debug("User banned (no active): {}", !byId.isActive());
        }
    }
}
