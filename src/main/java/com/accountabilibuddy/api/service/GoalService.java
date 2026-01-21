package com.accountabilibuddy.api.service;

import com.accountabilibuddy.api.model.Goal;
import com.accountabilibuddy.api.model.GoalCompletion;
import com.accountabilibuddy.api.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalService {
    private final GoalRepository _repo;
    private final GoalCompletionService _goalCompletionService;
    private final UserService _userService;

    public List<Goal> getAllGoalsByUserId(Principal principal) {
        List<Goal> goals = _repo.findGoalsByUserId(_userService.getCurrentUserId(principal));
        goals.forEach(goal -> {
            goal.setGoalCompletion(_goalCompletionService.findByGoalId(goal.getId()));
        });
        return goals;
    }

    public Goal getGoalById(Long goalId) {
        Goal goal = _repo.findById(goalId).orElseThrow();
        goal.setGoalCompletion(_goalCompletionService.findByGoalId(goalId));
        return goal;
    }

    public Goal saveNewGoal(Goal goal, Principal principal) {
        Long userId = _userService.getCurrentUserId(principal);
        goal.setStartDate(LocalDateTime.now());
        goal.setUserId(userId);
        goal = _repo.save(goal);
        GoalCompletion goalCompletion = new GoalCompletion();
        goalCompletion.setGoalId(goal.getId());
        goalCompletion.setUserId(userId);
        goalCompletion.setCompleted(0);
        goalCompletion.setTimeFrameEndDate(calculateTimeFrameEndDate(goal));
        goal.setGoalCompletion(_goalCompletionService.save(goalCompletion));
        return _repo.save(goal);
    }

    public Goal updateGoal(Long id, Goal newGoal) {
        Goal goal = _repo.findById(id).orElseThrow();
        goal.setTitle(newGoal.getTitle());
        goal.setTimeFrame(newGoal.getTimeFrame());
        goal.setCompletionsPerTimeFrame(newGoal.getCompletionsPerTimeFrame());
        goal.setEndDate(newGoal.getEndDate());
        goal.setGoalCompletion(_goalCompletionService.findByGoalId(id));
        goal.getGoalCompletion().setCompleted(0);
        goal.getGoalCompletion().setTimeFrameEndDate(calculateTimeFrameEndDate(goal));
        return _repo.save(goal);
    }

    public void deleteGoalById(Long id) {
        _repo.deleteById(id);
    }

    public void completeOnce(Long goalId) {
        GoalCompletion goalCompletion = _goalCompletionService.findByGoalId(goalId);
        goalCompletion.setCompleted(goalCompletion.getCompleted() + 1);
        _goalCompletionService.save(goalCompletion);
    }

    private LocalDateTime calculateTimeFrameEndDate(Goal goal) {
        String timeFrame = goal.getTimeFrame();
        LocalDateTime startTime = goal.getStartDate();

        return switch (timeFrame) {
            case "Daily" -> {
                startTime = startTime.plusDays(1);
                yield LocalDateTime.of(startTime.getYear(), startTime.getMonthValue(), startTime.getDayOfMonth(), 23, 59, 59);
            }
            case "Weekly" -> {
                startTime = startTime.plusDays(7);
                yield LocalDateTime.of(startTime.getYear(), startTime.getMonthValue(), startTime.getDayOfMonth(), 23, 59, 59);
            }
            case "Bi-Weekly" -> {
                startTime = startTime.plusDays(14);
                yield LocalDateTime.of(startTime.getYear(), startTime.getMonthValue(), startTime.getDayOfMonth(), 23, 59, 59);
            }
            case "Monthly" -> {
                startTime = startTime.plusMonths(1);
                yield LocalDateTime.of(startTime.getYear(), startTime.getMonthValue(), startTime.getDayOfMonth(), 23, 59, 59);
            }
            default -> goal.getEndDate();
        };
    }
}
