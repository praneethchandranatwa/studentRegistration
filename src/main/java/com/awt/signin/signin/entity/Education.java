package com.awt.signin.signin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="education")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "education_id")
    private Long educationId;

    @Column(name =" course_type")
    private String courseType;

    @Column(name = "year_of_passing")
    private int yearOfPassing;

    @Column(name = "university_name")
    private String universityName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Registration registration;
}