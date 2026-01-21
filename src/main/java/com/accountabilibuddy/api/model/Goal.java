package com.accountabilibuddy.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "goals")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String title;
    private String timeFrame;
    private int completionsPerTimeFrame;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime completionDate;

    @OneToOne(cascade = CascadeType.ALL)
    private GoalCompletion goalCompletion;
}
