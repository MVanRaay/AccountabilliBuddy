package com.accountabilibuddy.api.controller;

import ch.qos.logback.core.model.Model;
import com.accountabilibuddy.api.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendController {
    private final FriendService _service;

    @GetMapping
    public String getMyFriends(Model model, Principal principal) {
        // TODO: Retrieve user's friends
        return "friends"; // TODO: Create friends page
    }

    @PostMapping
    public String addFriend(Principal principal, @RequestParam Long friendId){
        _service.addFriend(principal, friendId);
        return "redirect:/friends";
    }
}
