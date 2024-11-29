package com.vishnu.controller;

import com.vishnu.entity.User;
import com.vishnu.repo.UserRepository;
import com.vishnu.service.JWTService;
import com.vishnu.service.AuthenticationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder; // Changed to PasswordEncoder

    public AuthController(UserRepository userRepository, JWTService jwtService, AuthenticationService authenticationService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Use PasswordEncoder
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }
}
