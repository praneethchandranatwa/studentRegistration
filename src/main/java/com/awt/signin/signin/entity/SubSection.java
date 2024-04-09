package com.awt.signin.signin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class SubSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  subSectionId;

    private String subsectionName;

    @OneToMany
    private List<ReferenceLink> referenceLinks;

    @JsonIgnore
     @ManyToOne
     @JoinColumn(name = "section_id")
     private Section section;

     @JsonIgnore
     @ManyToOne
     @JoinColumn(name = "course_id")
     private Courses courses;


     @OneToMany(cascade = CascadeType.ALL, mappedBy = "subSection")
     private List<Quiz> quizList;




}

