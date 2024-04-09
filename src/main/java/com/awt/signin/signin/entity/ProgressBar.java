package com.awt.signin.signin.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ProgressBar {

    public ProgressBar() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long progressBarId;

    private int newProgress;

    private int oldProgress;


}
