package com.example.kupiprodai.controllers;

import com.example.kupiprodai.models.User;
import com.example.kupiprodai.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//@RestController
@Controller
//@RequiredArgsConstructor
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

//    @GetMapping("/login2")
//    public String login() {
//        return "login";
//    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userService.createUser(user)) {
            log.debug("Пользователь с таким email уже существует: [{}]", user);
            model.addAttribute("errorMsg", "Пользователь с таким email уже существует");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/hello")
    public String securityUrl() {
        return "hello";
    }
}
