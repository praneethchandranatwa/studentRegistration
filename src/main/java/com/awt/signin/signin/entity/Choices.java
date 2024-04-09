package com.awt.signin.signin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Choices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long choiceId;

    private String content;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
