package org.example.springsecurity.repositories;


import org.example.springsecurity.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleByRole(Role.Roles roles);



}
