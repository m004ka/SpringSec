package org.example.springsecurity.service;



import org.example.springsecurity.dto.NewUserDTO;
import org.example.springsecurity.dto.UserDTO;
import org.example.springsecurity.model.User;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();
    User getUser(Long id);

    User createUser(NewUserDTO userDTO);

    User updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

    UserDTO setRoleDTO(UserDTO userDTO, String role);
}
