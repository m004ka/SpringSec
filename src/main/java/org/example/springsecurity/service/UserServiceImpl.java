package org.example.springsecurity.service;

import lombok.RequiredArgsConstructor;
import org.example.springsecurity.dto.NewUserDTO;
import org.example.springsecurity.dto.UserDTO;
import org.example.springsecurity.model.Role;
import org.example.springsecurity.model.User;
import org.example.springsecurity.repositories.RoleRepository;
import org.example.springsecurity.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAllUsers() {
        return UserDTO.getDTOList(userRepository.findAll());
    }

    @Override
    public User getUser(Long id) {
        return userRepository.getUserById(id).orElseThrow(() -> new RuntimeException("Пользователь с id = " + id + " не найден"));
    }

    @Override
    public User createUser(NewUserDTO userDTO) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getRoleByRole(Role.Roles.ROLE_USER));

        User user = User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .roles(roles)
                .build();

        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.getUserById(id).orElseThrow(() -> new RuntimeException("Пользователь с id = " + id + " не найден"));

        if (userDTO.getUsername() != null) {
            user.setUsername(userDTO.getUsername());
        }
        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }
        user.setRoles(userDTO.getRoles());
        System.out.println("Пароль" + userDTO.getPassword());
        if (userDTO.getPassword() != null) {
            System.out.println("Пароль" + userDTO.getPassword());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        userRepository.save(user);

        return user;
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.getUserById(id).orElseThrow(() -> new RuntimeException("Пользователь с id = " + id + " не найден"));

        userRepository.delete(user);
    }

    public UserDTO setRoleDTO(UserDTO userDTO, String role) {
        Set<Role> roles = new HashSet<>();
        Role userRole;
        if (role == "ROLE_USER") {
            userRole = roleRepository.getRoleByRole(Role.Roles.ROLE_USER);
        } else {
            userRole = roleRepository.getRoleByRole(Role.Roles.ROLE_ADMIN);
        }
        roles.add(userRole);
        userDTO.setRoles(roles);

        return userDTO;
    }
}
