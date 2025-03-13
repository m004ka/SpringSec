package org.example.springsecurity.service;

import org.example.springsecurity.dto.NewUserDTO;
import org.example.springsecurity.dto.UserDTO;
import org.example.springsecurity.model.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResponseService {
    ResponseEntity<String> updateResponse(Long id, UserDTO userDTO, String role);

    ResponseEntity<String> createResponse(NewUserDTO userDTO);

    ResponseEntity<String> deleteResponse(Long id);

    ResponseEntity<UserDTO> getUserResponse(Long id);

    ResponseEntity<List<UserDTO>> getUsers();

    ResponseEntity<List<Role>> getAllRoles();
}
