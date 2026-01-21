package com.accountabilibuddy.api.service;

import com.accountabilibuddy.api.model.Friend;
import com.accountabilibuddy.api.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository _repo;

    public List<Friend> findAll() {
        return _repo.findAll();
    }
}
