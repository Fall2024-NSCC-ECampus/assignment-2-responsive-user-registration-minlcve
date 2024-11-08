package com.example.userregistration.controller;

import com.example.userregistration.model.User;
import com.example.userregistration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("error", "Email already exists");
            return "register";
        }

        userService.saveUser(user);
        model.addAttribute("success", "Registration successful!");
        return "login";  // Redirect to login after successful registration
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(String username, String password, Model model) {
        User user = userService.findByUsername(username).orElse(null);
        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }

        return "home";
    }
}
