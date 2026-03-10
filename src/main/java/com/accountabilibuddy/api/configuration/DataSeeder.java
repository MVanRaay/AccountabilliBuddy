package com.accountabilibuddy.api.configuration;

import com.accountabilibuddy.api.model.Goal;
import com.accountabilibuddy.api.model.GoalCompletion;
import com.accountabilibuddy.api.model.User;
import com.accountabilibuddy.api.repository.GoalCompletionRepository;
import com.accountabilibuddy.api.repository.GoalRepository;
import com.accountabilibuddy.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final GoalRepository goalRepository;
    private final GoalCompletionRepository goalCompletionRepository;

    @Override
    public void run(String... args) {
        seedUsersAndGoals();
    }

    private void seedUsersAndGoals() {
        User userOne = userRepository.findByEmail("user.one@example.com")
                .orElseGet(() -> {
                    User u = new User();
                    u.setFirstName("User");
                    u.setLastName("One");
                    u.setEmail("user.one@example.com");
                    u.setRegisteredOn(LocalDateTime.now());
                    u.setProfileComplete(true);
                    return userRepository.save(u);
                });

        User userTwo = userRepository.findByEmail("user.two@example.com")
                .orElseGet(() -> {
                    User u = new User();
                    u.setFirstName("User");
                    u.setLastName("Two");
                    u.setEmail("user.two@example.com");
                    u.setRegisteredOn(LocalDateTime.now());
                    u.setProfileComplete(true);
                    return userRepository.save(u);
                });

        if (goalRepository.findGoalsByUserId(userOne.getId()).isEmpty()) {
            createGoalsForUser(userOne,
                    "Read for 20 minutes",
                    "Daily",
                    1,
                    "Workout 3x per week",
                    "Weekly",
                    3);
        }

        if (goalRepository.findGoalsByUserId(userTwo.getId()).isEmpty()) {
            createGoalsForUser(userTwo,
                    "Practice guitar",
                    "Daily",
                    2,
                    "Call family",
                    "Weekly",
                    1);
        }
    }

    private void createGoalsForUser(User user,
                                    String title1,
                                    String timeframe1,
                                    int completions1,
                                    String title2,
                                    String timeframe2,
                                    int completions2) {

        LocalDateTime now = LocalDateTime.now();

        Goal goal1 = new Goal();
        goal1.setUserId(user.getId());
        goal1.setTitle(title1);
        goal1.setTimeFrame(timeframe1);
        goal1.setCompletionsPerTimeFrame(completions1);
        goal1.setStartDate(now);
        goal1.setEndDate(now.plusMonths(1));
        goal1 = goalRepository.save(goal1);

        GoalCompletion completion1 = new GoalCompletion();
        completion1.setGoalId(goal1.getId());
        completion1.setUserId(user.getId());
        completion1.setCompleted(0);
        completion1.setTimeFrameEndDate(calculateTimeFrameEndDate(now, timeframe1));
        goalCompletionRepository.save(completion1);

        Goal goal2 = new Goal();
        goal2.setUserId(user.getId());
        goal2.setTitle(title2);
        goal2.setTimeFrame(timeframe2);
        goal2.setCompletionsPerTimeFrame(completions2);
        goal2.setStartDate(now);
        goal2.setEndDate(now.plusMonths(1));
        goal2 = goalRepository.save(goal2);

        GoalCompletion completion2 = new GoalCompletion();
        completion2.setGoalId(goal2.getId());
        completion2.setUserId(user.getId());
        completion2.setCompleted(0);
        completion2.setTimeFrameEndDate(calculateTimeFrameEndDate(now, timeframe2));
        goalCompletionRepository.save(completion2);
    }

    private LocalDateTime calculateTimeFrameEndDate(LocalDateTime start, String timeFrame) {
        return switch (timeFrame) {
            case "Daily" -> LocalDateTime.of(
                    start.plusDays(1).toLocalDate(),
                    LocalDateTime.of(0, 1, 1, 23, 59, 59).toLocalTime());
            case "Weekly" -> LocalDateTime.of(
                    start.plusWeeks(1).toLocalDate(),
                    LocalDateTime.of(0, 1, 1, 23, 59, 59).toLocalTime());
            case "Bi-Weekly" -> LocalDateTime.of(
                    start.plusWeeks(2).toLocalDate(),
                    LocalDateTime.of(0, 1, 1, 23, 59, 59).toLocalTime());
            case "Monthly" -> LocalDateTime.of(
                    start.plusMonths(1).toLocalDate(),
                    LocalDateTime.of(0, 1, 1, 23, 59, 59).toLocalTime());
            default -> start;
        };
    }
}
