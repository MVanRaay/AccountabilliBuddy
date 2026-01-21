package com.accountabilibuddy.api.configuration;

import com.accountabilibuddy.api.service.FriendService;
import com.accountabilibuddy.api.service.GoalCompletionService;
import com.accountabilibuddy.api.service.GoalService;
import com.accountabilibuddy.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
//@RequiredArgsConstructor(onConstructor_ = @Autowired)
//public class DataSeeder implements CommandLineRunner {
//    private final UserService userService;
//    private final GoalService goalService;
//    private final FriendService friendService;
//    private final GoalCompletionService goalCompletionService;
//
//
//    @Override
//    public void run(String... args) throws Exception {
//        if (userService.getAllUsers().isEmpty() || goalService.getGoalById(1L) == null || friendService.findAll().isEmpty() || goalCompletionService.findByGoalId(1L) == null) {
//            seedData();
//        }
//    }
//
//    private void seedData() {
//
//    }
//}
