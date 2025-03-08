package org.example.usercrud.service;

import org.example.usercrud.dto.UserDTO;
import org.example.usercrud.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUser(Long id);

    User createUser(UserDTO userDTO);

    User updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

}
