package org.example.springsecurity.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.springsecurity.model.Role;
import org.example.springsecurity.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleInitializer {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        if (roleRepository.count() == 0) {
            Role userRole = Role.builder()
                    .role(Role.Roles.ROLE_USER)
                    .build();
            Role adminRole = Role.builder().
                    role(Role.Roles.ROLE_ADMIN)
                    .build();

            roleRepository.save(userRole);
            roleRepository.save(adminRole);
        }
    }
}