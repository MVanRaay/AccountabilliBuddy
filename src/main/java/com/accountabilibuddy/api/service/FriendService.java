package com.accountabilibuddy.api.service;

import com.accountabilibuddy.api.model.Friend;
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

    public List<Friend> findAll() {
        return _repo.findAll();
    }

    public Friend addFriend(Principal principal, Long friendId) {
        Friend friend = new Friend();
        friend.setFriendOneId(_userService.getCurrentUserId(principal));
        friend.setFriendTwoId(friendId);
        return _repo.save(friend);
    }
}
