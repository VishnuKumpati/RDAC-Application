package com.vishnu.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vishnu.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
