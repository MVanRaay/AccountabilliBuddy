package com.accountabilibuddy.api.repository;

import com.accountabilibuddy.api.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Long> {
}
