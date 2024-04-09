package com.awt.signin.signin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;

    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quiz")
    private List<Question> questions;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sub_section_id")
    private SubSection subSection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quiz")
    private List<QuizHistory> quizHistory;

}
