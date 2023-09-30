package com.example.kupiprodai.controllers;

import com.example.kupiprodai.models.User;
import com.example.kupiprodai.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMINISTRATOR')")
public class AdminController {
    private final UserService userService;

    @GetMapping("/users")
    public String users(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "admin";
    }

    @PostMapping("/user/ban/{userId}")
    public String userBan(@PathVariable("userId") Long userId) {
        userService.userBаnById(userId);
        return "redirect:/admin/users";
    }

//    @GetMapping("/user/edit/{id}")
//    @PostMapping("/user/edit/")
}
