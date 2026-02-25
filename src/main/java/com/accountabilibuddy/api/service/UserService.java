package com.accountabilibuddy.api.service;

import com.accountabilibuddy.api.dto.UserSearchDto;
import com.accountabilibuddy.api.model.User;
import com.accountabilibuddy.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository _repo;

    public Long getCurrentUserId(Principal principal) {
        return _repo.findByGoogleId(principal.getName())
                .orElseThrow(() -> new IllegalStateException(
                        "Authenticated user not found in database"
                ))
                .getId();
    }

    public List<User> getAllUsers() {
        return _repo.findAll();
    }

    public User getUserById(Long id) {
        return _repo.findUserById(id);
    }

    public User saveNewUser(String firstName, String lastName, Principal principal) {
        User user = _repo.findByGoogleId(principal.getName()).orElseThrow();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRegisteredOn(LocalDateTime.now());
        user.setProfileComplete(true);
        return _repo.save(user);
    }

    public User updateUser(User newUser, Principal principal) {
        User user = _repo.findById(getCurrentUserId(principal)).orElseThrow();
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        return _repo.save(user);
    }

    public void deleteCurrentUser(Principal principal) {
        _repo.deleteById(getCurrentUserId(principal));
    }

    public List<UserSearchDto> searchUsers(Long currentUserId, String searchTerm) {
        return _repo.searchUsers(searchTerm, currentUserId);
    }
}
