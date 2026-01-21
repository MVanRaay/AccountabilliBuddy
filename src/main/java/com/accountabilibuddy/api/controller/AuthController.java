package com.accountabilibuddy.api.controller;

import com.accountabilibuddy.api.model.User;
import com.accountabilibuddy.api.service.GoalService;
import com.accountabilibuddy.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping
    public String redirect(Principal principal) {
        if (userService.getCurrentUserId(principal) == null) {
            return "redirect:/login";
        } else if (userService.getUserById(userService.getCurrentUserId(principal)) == null) {
            return "redirect:/users/register";
        } else {
            return "redirect:/goals";
        }
    }
}
