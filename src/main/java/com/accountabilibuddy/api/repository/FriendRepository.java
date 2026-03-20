package com.accountabilibuddy.api.repository;

import com.accountabilibuddy.api.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    List<Friend> findByFriendOneIdOrFriendTwoId(Long friendOneId, Long friendTwoId);

    boolean existsByFriendOneIdAndFriendTwoId(Long friendOneId, Long friendTwoId);

    Friend findByFriendOneIdAndFriendTwoId(Long first, Long second);
}
