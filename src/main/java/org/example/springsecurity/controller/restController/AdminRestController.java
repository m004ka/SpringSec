package org.example.springsecurity.controller.restController;

import lombok.RequiredArgsConstructor;
import org.example.springsecurity.dto.NewUserDTO;
import org.example.springsecurity.dto.UserDTO;
import org.example.springsecurity.model.Role;
import org.example.springsecurity.model.User;
import org.example.springsecurity.repositories.RoleRepository;
import org.example.springsecurity.repositories.UserRepository;
import org.example.springsecurity.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminRestController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Пользователь с id = " + id + " не найден"));
        return ResponseEntity.ok(UserDTO.getDTO(user));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody NewUserDTO newUserDTO) {
        if (userRepository.existsByUsername(newUserDTO.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Пользователь с данным именем уже существует");
        }
        userService.createUser(newUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь успешно создан");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO, @RequestParam String role) {
        System.out.println("Вот дто\n\n\n"  + userDTO + "Вот дто\n\n\n" );
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Пользователь с таким username уже существует");
        }
        if (UserDTO.ValidateDTO(userDTO) || role == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Заполните все поля корректно");
        }



        userService.updateUser(id, userService.setRoleDTO(userDTO, role));
        return ResponseEntity.ok("Пользователь успешно обновлен");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Пользователь успешно удален");
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return ResponseEntity.ok(roles);
    }
}
