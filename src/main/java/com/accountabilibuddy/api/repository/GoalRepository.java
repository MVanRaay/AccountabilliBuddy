package com.accountabilibuddy.api.repository;

import com.accountabilibuddy.api.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findGoalsByUserId(Long userId);
}
