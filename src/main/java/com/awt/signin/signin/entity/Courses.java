package com.awt.signin.signin.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Courses {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int course_id;

    private String courseName;


    @OneToMany(mappedBy = "courses", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Section> sections;
}



