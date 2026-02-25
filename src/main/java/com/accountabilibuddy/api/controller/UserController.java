package com.accountabilibuddy.api.controller;

import com.accountabilibuddy.api.model.User;
import com.accountabilibuddy.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService _service;

    @GetMapping("/register")
    public String showForm(Model model, Principal principal) {
        User user = _service.getUserById(_service.getCurrentUserId(principal));
        model.addAttribute("user", user);
        return "userForm";
    }

    @PostMapping("/register")
    public String processForm(@RequestParam String firstName,
                              @RequestParam String lastName,
                              RedirectAttributes redirectAttributes,
                              Principal principal) {

        if (firstName.isBlank() || lastName.isBlank()) {
            redirectAttributes.addFlashAttribute("error", "Fields cannot be empty or blank");
            return "redirect:/users/register";
        }

        _service.saveNewUser(firstName, lastName, principal);
        return "redirect:/goals";
    }
}
