package com.vishnu.service;

import com.vishnu.entity.Role;
import com.vishnu.entity.User;
import com.vishnu.repo.UserRepository;
import com.vishnu.securityConfig.CustomUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new CustomUserDetails(user);
    }

    public void saveUser(User user) {
        // Encrypt the user's password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Assign roles to the user
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Set<Role> roles = new HashSet<>();
            Role defaultRole = new Role();
            defaultRole.setName("ROLE_USER");
            roles.add(defaultRole);
            user.setRoles(roles);
        }

        // Save the user to the database
        userRepository.save(user);
    }
}
