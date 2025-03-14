package org.example.springsecurity.controller.restController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.springsecurity.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {
    @GetMapping
    public ResponseEntity<UserDTO> getUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(UserDTO.getDTOFromRequest(request));
    }
}
