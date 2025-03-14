package org.example.springsecurity.controller.restController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springsecurity.dto.NewUserDTO;
import org.example.springsecurity.dto.UserDTO;
import org.example.springsecurity.model.Role;
import org.example.springsecurity.service.ResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminRestController {

    private final ResponseService responseService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return responseService.getUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return responseService.getUserResponse(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@Valid @RequestBody NewUserDTO newUserDTO) {
        return responseService.createResponse(newUserDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO, @RequestParam String role) {
        return responseService.updateResponse(id, userDTO, role);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return responseService.deleteResponse(id);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {

        return responseService.getAllRoles();
    }
}
