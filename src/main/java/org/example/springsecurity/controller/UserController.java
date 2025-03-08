package org.example.springsecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.springsecurity.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/user")
    public String getUserInfo(HttpServletRequest request, Model model) {
        model.addAttribute("userDTO", UserDTO.getDTOFromRequest(request));
        return "user";
    }

}
