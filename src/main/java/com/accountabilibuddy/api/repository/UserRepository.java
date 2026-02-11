package com.accountabilibuddy.api.repository;

import com.accountabilibuddy.api.dto.UserSearchDto;
import com.accountabilibuddy.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    User findUserById(Long id);


    Optional<User> findByGoogleId(String googleId);

    @Query("""
        SELECT new com.accountabilibuddy.api.dto.UserSearchDto(
            u.id, u.firstName, u.lastName, u.email
        )
        FROM User u
        WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
               AND u.id <> :currentUserId
           OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
               AND u.id <> :currentUserId
           OR LOWER(u.email) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
               AND u.id <> :currentUserId
    """)
    List<UserSearchDto> searchUsers(@Param("searchTerm") String searchTerm, @Param("currentUserId") Long currentUserId);
}
