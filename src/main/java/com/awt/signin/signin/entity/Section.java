package com.awt.signin.signin.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


import java.util.List;


@Entity
@Data
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int section_id;

    private String sectionName;

    private int percentage;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Courses courses;


    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SubSection> subSections;

}

