package com.vishnu.service;

import com.vishnu.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthenticationService {

    private final MyUserDetailsService myUserDetailsService;
    private final PasswordEncoder passwordEncoder; // Changed to PasswordEncoder

    public AuthenticationService(MyUserDetailsService myUserDetailsService, PasswordEncoder passwordEncoder) {
        this.myUserDetailsService = myUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    public User authenticate(String username, String password) {
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
        if (passwordEncoder.matches(password, userDetails.getPassword())) { // Use PasswordEncoder
            return (User) userDetails;
        }
        return null;
    }
}
