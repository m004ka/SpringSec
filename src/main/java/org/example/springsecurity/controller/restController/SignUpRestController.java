package org.example.springsecurity.controller.restController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springsecurity.dto.NewUserDTO;
import org.example.springsecurity.repositories.UserRepository;
import org.example.springsecurity.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class SignUpRestController {

    private final UserRepository userRepository;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody NewUserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            return ResponseEntity.status(400).body("Пользователь с таким username уже существует");
        }

        userService.createUser(userDTO);
        return ResponseEntity.status(201).body("Пользователь успешно зарегистрирован");
    }
}
