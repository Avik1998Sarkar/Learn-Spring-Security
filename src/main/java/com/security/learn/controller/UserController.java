package com.security.learn.controller;

import com.security.learn.entity.User;
import com.security.learn.repository.UserRepository;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public String loginAPI(@RequestBody User user) {
        User userDetails = userRepository.findByUsername(user.getUsername());
        if (ObjectUtils.isEmpty(userDetails)) {
            return "Login Failed";
        } else {
            return "Login Successful";
        }
    }
}
