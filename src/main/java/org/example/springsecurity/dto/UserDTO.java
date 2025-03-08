package org.example.springsecurity.dto;


import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springsecurity.model.Role;
import org.example.springsecurity.model.User;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private String email;

    private Set<Role> roles;


    public static UserDTO getDTO(User user) {
        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();

        return userDTO;
    }

    public static List<UserDTO> getDTOList(List<User> users) {
        return users.stream()
                .map(UserDTO::getDTO)
                .collect(Collectors.toList());
    }

    public static UserDTO getDTOFromRequest(HttpServletRequest request) {
        Authentication authentication = (Authentication) request.getUserPrincipal();
        User user = (User) authentication.getPrincipal();
        return UserDTO.getDTO(user);
    }

    public static boolean ValidateDTO(UserDTO userDTO) {
        return !userDTO.getUsername().isEmpty() && !userDTO.getEmail().isEmpty() && !userDTO.getPassword().isEmpty();
    }

}
