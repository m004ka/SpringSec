package org.example.springsecurity.service;

import lombok.RequiredArgsConstructor;

import org.example.springsecurity.dto.NewUserDTO;
import org.example.springsecurity.dto.UserDTO;
import org.example.springsecurity.model.Role;
import org.example.springsecurity.model.User;
import org.example.springsecurity.repositories.RoleRepository;
import org.example.springsecurity.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService {

    private final UserRepository userRepository;

    private final UserService userService;

    private final RoleRepository roleRepository;

    @Override
    public ResponseEntity<String> updateResponse(Long id, UserDTO userDTO, String role) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("Вы обновляете несуществующего пользователя");
        }
        User user = userRepository.getUserById(id).orElseThrow(() -> new RuntimeException("Аккаунт не существует"));
        if (!Objects.equals(user.getUsername(), userDTO.getUsername())) {
            if (userRepository.existsByUsername(userDTO.getUsername())) {
                return ResponseEntity.badRequest().body("Новый юзернейм который вы хотите установить уже занят, пожалуйста выберите другой");
            }
        }

        userService.updateUser(id, userService.setRoleDTO(userDTO, role));
        return ResponseEntity.ok().body("Пользователь успешно обновлён");
    }

    @Override
    public ResponseEntity<String> createResponse(NewUserDTO userDTO) {
        if(userRepository.existsByUsername(userDTO.getUsername())){
            return ResponseEntity.badRequest().body("Пользователь с username: " + userDTO.getUsername() + " уже существует");
        }
        userService.createUser(userDTO);
        return ResponseEntity.ok("Пользователь успешно создан");
    }

    @Override
    public ResponseEntity<String> deleteResponse(Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("Пользователь с id = " + id + " не найден для удаления");
        }
        return ResponseEntity.ok("Пользователь успешно удален");
    }

    @Override
    public ResponseEntity<UserDTO> getUserResponse(Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.badRequest().build();
        }

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Аккаунт не существует"));

        return ResponseEntity.ok(UserDTO.getDTO(user));
    }

    @Override
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> users = userService.getAllUsers();

        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }

        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
        return ResponseEntity.ok(roles);
    }
}
