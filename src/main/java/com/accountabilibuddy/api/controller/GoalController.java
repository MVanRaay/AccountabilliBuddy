package com.accountabilibuddy.api.controller;

import com.accountabilibuddy.api.model.Goal;
import com.accountabilibuddy.api.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/goals")
@RequiredArgsConstructor
public class GoalController {
    private final GoalService _service;

    @GetMapping
    public String listGoals(Model model, Principal principal) {
        model.addAttribute("goals", _service.getAllGoalsByUserId(principal));
        model.addAttribute("numbers", List.of(0, 1, 2, 3));
        Map<String, String> timeFrameDict = Map.of(
                "Daily", "Day",
                "Weekly", "Week",
                "Bi-Weekly", "Two Weeks",
                "Monthly", "Month",
                "Once", "Once"
        );
        model.addAttribute("timeframeDict", timeFrameDict);
        return "goals";
    }

    @PutMapping("/{goalId}/complete")
    public String updateGoalCompletion(
            @PathVariable Long goalId,
            @RequestParam int completed,
            Principal principal) {
        _service.updateCompletion(goalId, completed, principal);
        return "redirect:/goals";
    }

    @GetMapping("/new")
    public String addGoal(Model model) {
        model.addAttribute("goal", new Goal());
        return "goalForm";
    }

    @PostMapping("/new")
    public String addGoal(
            @ModelAttribute Goal goal,
            @RequestParam(value = "endDateRaw", required = false) String endDateRaw,
            Principal principal) {

        if (endDateRaw != null && !endDateRaw.isBlank()) {
            LocalDate date = LocalDate.parse(endDateRaw);
            goal.setEndDate(date.atTime(23, 59, 59));
        } else {
            goal.setEndDate(null);
        }

        _service.saveNewGoal(goal, principal);
        return "redirect:/goals";
    }
}
