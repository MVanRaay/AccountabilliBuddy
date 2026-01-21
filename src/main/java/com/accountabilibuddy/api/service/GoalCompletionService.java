package com.accountabilibuddy.api.service;

import com.accountabilibuddy.api.model.GoalCompletion;
import com.accountabilibuddy.api.repository.GoalCompletionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalCompletionService {
    private final GoalCompletionRepository goalCompletionRepository;

    public GoalCompletion findByGoalId(Long goalId) {
        return goalCompletionRepository.findByGoalId(goalId);
    }

    public GoalCompletion save(GoalCompletion goalCompletion) {
        return goalCompletionRepository.save(goalCompletion);
    }
}
