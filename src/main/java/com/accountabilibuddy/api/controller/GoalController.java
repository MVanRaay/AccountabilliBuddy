package com.accountabilibuddy.api.controller;

import com.accountabilibuddy.api.model.Goal;
import com.accountabilibuddy.api.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/goals")
@RequiredArgsConstructor
public class GoalController {
    private final GoalService _service;

    @GetMapping
    public String listGoals(Model model, Principal principal) {
        model.addAttribute("goals", _service.getAllGoalsByUserId(principal));
        model.addAttribute("numbers", List.of(0, 1, 2, 3));
        return "goals";
    }

    @PostMapping("/{goalId}/complete")
    public String updateGoalCompletion(@PathVariable Long goalId) {
        _service.completeOnce(goalId);
        return "redirect:/goals";
    }

    @GetMapping("/new")
    public String addGoal(Model model) {
        model.addAttribute("goal", new Goal());
        return "goalForm";
    }

    @PostMapping("/new")
    public String addGoal(@ModelAttribute Goal goal, Principal principal) {
        _service.saveNewGoal(goal, principal);
        return "redirect:/goals";
    }
}
