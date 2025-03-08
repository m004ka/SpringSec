package org.example.springsecurity.model;

import jakarta.persistence.*;
import lombok.*;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Roles role;
    @Override
    public String getAuthority() {
        return role.name();
    }


    public enum Roles {
        ROLE_USER,
        ROLE_ADMIN;
    }
}


