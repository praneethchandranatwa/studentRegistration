package com.awt.signin.signin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class QuizHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizHistoryId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Registration registration;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime submissionDate;

    private int score;


}