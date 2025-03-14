package org.example.springsecurity.controller.restController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springsecurity.dto.NewUserDTO;
import org.example.springsecurity.service.ResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class SignUpRestController {

    private final ResponseService responseService;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody NewUserDTO userDTO) {
        return responseService.createResponse(userDTO);
    }
}
