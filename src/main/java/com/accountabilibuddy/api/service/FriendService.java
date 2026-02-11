package com.accountabilibuddy.api.service;

import com.accountabilibuddy.api.dto.UserSearchDto;
import com.accountabilibuddy.api.model.Friend;
import com.accountabilibuddy.api.model.User;
import com.accountabilibuddy.api.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository _repo;
    private final UserService _userService;

    public List<UserSearchDto> searchUsers(Principal principal, String searchTerm) {
        return _userService.searchUsers(_userService.getCurrentUserId(principal), searchTerm);
    }

    public Friend addFriend(Principal principal, Long friendId) {
        Friend friend = new Friend();
        friend.setFriendOneId(_userService.getCurrentUserId(principal));
        friend.setFriendTwoId(friendId);
        return _repo.save(friend);
    }
}
