package com.accountabilibuddy.api.repository;

import com.accountabilibuddy.api.model.GoalCompletion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalCompletionRepository extends JpaRepository<GoalCompletion, Long> {
    GoalCompletion findByGoalId(Long goalId);

    List<GoalCompletion> findGoalCompletionsByUserId(Long userId);
}
