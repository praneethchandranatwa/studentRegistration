package com.awt.signin.signin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    private String content;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private List<Choices> choices;

    private int correctOptionIndex;


}
