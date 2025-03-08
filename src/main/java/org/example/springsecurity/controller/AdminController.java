package org.example.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.example.springsecurity.dto.NewUserDTO;
import org.example.springsecurity.dto.UserDTO;
import org.example.springsecurity.model.Role;
import org.example.springsecurity.model.User;
import org.example.springsecurity.repositories.RoleRepository;
import org.example.springsecurity.repositories.UserRepository;
import org.example.springsecurity.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("allusers", userService.getAllUsers());
        model.addAttribute("allRoles", roleRepository.findAll());

        return "allUsers";
    }

    @GetMapping("/create")
    public String createUser() {
        return "createUser";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute NewUserDTO newUserDTO, Model model) {
        if (userRepository.existsByUsername(newUserDTO.getUsername())) {
            model.addAttribute("errors", "Пользователь с данным именем уже существует");
            return "createUser";
        }
        userService.createUser(newUserDTO);
        return "redirect:/admin";
    }

    @GetMapping("/update/{id}")
    public String updateUserForm(@PathVariable Long id, Model model) {
        User user = userRepository.getUserById(id).orElseThrow(() -> new RuntimeException("Пользователь с id = " + id + " не найден"));

        UserDTO userDTO = UserDTO.getDTO(user);
        Set<String> roleNames = userDTO.getRoles().stream().map(role -> role.getAuthority()).collect(Collectors.toSet());

        model.addAttribute("userDTO", userDTO);
        model.addAttribute("userRoles", roleNames);
        model.addAttribute("allRoles", roleRepository.findAll());

        return "userUpdate";
    }


    @PostMapping("/update")
    public String updateUser(@ModelAttribute UserDTO userDTO, @RequestParam String role, @RequestParam Long id, Model model) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            model.addAttribute("errors", "Пользователь с таким username уже существует");
            return "userUpdate";
        }
        if (UserDTO.ValidateDTO(userDTO) || role == null) {
            model.addAttribute("errors", "Заполните все поля корректно");
            return "userUpdate";
        }

        userService.updateUser(id, userService.setRoleDTO(userDTO, role));

        return "redirect:/admin";
    }


    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
