package com.awt.signin.signin.entity;



import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="user_id")
    private Long userId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="user_name")
    private String userName;

    @Column(name="user_email")
    private String userEmail;

    @Column(name="user_password")
    private String userPassword;

    @Column(name="desired_role")
    private String desiredRole;

    @Column(name="isEnabled")
    private boolean isEnabled;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Education> educations;

    @Column(name="has_job_experience")
    private boolean hasJobExperience;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<JobExperience> jobExperiences;

    @OneToMany(cascade = CascadeType.ALL)
    private List<QuizHistory> quizHistory;



    public Registration() {
    }


    public Registration(Long userId, String firstName, String lastName, String userName, String userEmail, String userPassword, String desiredRole, boolean isEnabled, List<Education> educations, boolean hasJobExperience, List<JobExperience> jobExperiences, String message, int progress) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.desiredRole = desiredRole;
        this.isEnabled = isEnabled;
        this.educations = educations;
        this.hasJobExperience = hasJobExperience;
        this.jobExperiences = jobExperiences;
    }

    public Registration(String firstName, String lastName, String userName, String userEmail, String userPassword, String desiredRole, boolean isEnabled, List<Education> educations, boolean hasJobExperience, List<JobExperience> jobExperiences) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.desiredRole = desiredRole;
        this.isEnabled = isEnabled;
        this.educations = educations;
        this.hasJobExperience = hasJobExperience;
        this.jobExperiences = jobExperiences;

    }



    public Registration(String userName, String userEmail, String userPassword, boolean isEnabled) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.isEnabled = isEnabled;
    }


}
