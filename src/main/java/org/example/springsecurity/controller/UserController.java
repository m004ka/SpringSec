package org.example.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/user")
    public String getUserInfo() {
        return "userjs";
    }

}
