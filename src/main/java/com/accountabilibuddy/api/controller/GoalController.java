package com.accountabilibuddy.api.controller;

import com.accountabilibuddy.api.model.Goal;
import com.accountabilibuddy.api.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
            @RequestParam Boolean checked,
            Principal principal) {
        _service.updateCompletion(goalId, completed, checked, principal);
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

    @GetMapping("/{goalId}")
    public String viewGoal(@PathVariable Long goalId, Model model) {
        Goal goal = _service.getGoalById(goalId);
        if (goal == null) return "redirect:/goals";
        model.addAttribute("goal", goal);

        return "viewGoal";
    }

    @PostMapping("/{goalId}")
    public String updateGoal(@PathVariable Long goalId, @ModelAttribute Goal updatedGoal) {
        _service.updateGoal(goalId, updatedGoal);
        return "redirect:/goals";
    }

    @GetMapping("/{goalId}/delete")
    public String deleteGoal(@PathVariable Long goalId) {
        _service.deleteGoalById(goalId);
        return "redirect:/goals";
    }
}
