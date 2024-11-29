package com.vishnu.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vishnu.entity.User;

public interface UserManagementService {
    void saveUser(User user);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
