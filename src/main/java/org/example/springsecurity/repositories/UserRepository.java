package org.example.springsecurity.repositories;

import org.example.springsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserById(Long id);

    boolean existsByUsername(String username);
}
