package com.accountabilibuddy.api.controller;

import com.accountabilibuddy.api.dto.UserSearchDto;
import com.accountabilibuddy.api.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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

    @GetMapping("/find")
    public String findFriends() {
        return "searchFriends";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<UserSearchDto> searchFriends(
            @RequestParam String searchTerm,
            Principal principal) {

        if (searchTerm == null || searchTerm.isBlank()) {
            return List.of();
        }

        return _service.searchUsers(principal, searchTerm);
    }

    @PostMapping
    public String addFriend(Principal principal, @RequestParam Long friendId){
        _service.addFriend(principal, friendId);
        return "redirect:/friends";
    }
}
