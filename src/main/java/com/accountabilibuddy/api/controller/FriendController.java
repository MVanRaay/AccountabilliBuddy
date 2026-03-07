package com.accountabilibuddy.api.controller;

import com.accountabilibuddy.api.dto.UserSearchDto;
import com.accountabilibuddy.api.model.Goal;
import com.accountabilibuddy.api.model.User;
import com.accountabilibuddy.api.service.FriendService;
import com.accountabilibuddy.api.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendController {
    private final FriendService _service;
    private final GoalService _goalService;

    @GetMapping
    public String getMyFriends(Model model, Principal principal) {
        List<User> friends = _service.getFriendsForCurrentUser(principal);

        Map<Long, List<Goal>> friendGoals = new HashMap<>();
        for (User friend : friends) {
            friendGoals.put(friend.getId(), _goalService.getGoalsForUser(friend.getId()));
        }

        model.addAttribute("friends", friends);
        model.addAttribute("friendGoals", friendGoals);
        return "friends";
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
