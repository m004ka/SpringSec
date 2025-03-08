package org.example.springsecurity.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springsecurity.dto.NewUserDTO;
import org.example.springsecurity.repositories.UserRepository;
import org.example.springsecurity.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SignUpController {

    private final UserRepository userRepository;
    private final UserService userService;


    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(@Valid NewUserDTO userDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        if (userRepository.existsByUsername(userDTO.getUsername())) {
            model.addAttribute("errors", "Пользователь с таким username уже существует");
            return "signup";
        }

        userService.createUser(userDTO);
        return "redirect:/login";
    }

}
