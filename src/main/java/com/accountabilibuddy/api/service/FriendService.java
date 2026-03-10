package com.accountabilibuddy.api.service;

import com.accountabilibuddy.api.dto.UserSearchDto;
import com.accountabilibuddy.api.model.Friend;
import com.accountabilibuddy.api.model.User;
import com.accountabilibuddy.api.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
<<<<<<< rdooley/friends-view
import java.util.ArrayList;
=======
>>>>>>> main
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
<<<<<<< rdooley/friends-view
        Long currentUserId = _userService.getCurrentUserId(principal);

        Long first = Math.min(currentUserId, friendId);
        Long second = Math.max(currentUserId, friendId);

        if (_repo.existsByFriendOneIdAndFriendTwoId(first, second)) {
            return null;
        }

        Friend friend = new Friend();
        friend.setFriendOneId(first);
        friend.setFriendTwoId(second);
        return _repo.save(friend);
    }

    public List<User> getFriendsForCurrentUser(Principal principal) {
        Long currentUserId = _userService.getCurrentUserId(principal);
        List<Friend> friendships = _repo.findByFriendOneIdOrFriendTwoId(currentUserId, currentUserId);

        List<User> friends = new ArrayList<>();
        for (Friend friendship : friendships) {
            Long otherUserId = friendship.getFriendOneId().equals(currentUserId)
                    ? friendship.getFriendTwoId()
                    : friendship.getFriendOneId();
            friends.add(_userService.getById(otherUserId));
        }

        return friends;
=======
        Friend friend = new Friend();
        friend.setFriendOneId(_userService.getCurrentUserId(principal));
        friend.setFriendTwoId(friendId);
        return _repo.save(friend);
>>>>>>> main
    }
}
