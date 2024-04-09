package com.awt.signin.signin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "job_experience")
public class JobExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_experience_id")
    private Long jobExperienceId;

    @Column(name = "company_title")
    private String companyTitle;

    @Embedded
    private JobDuration jobDuration;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Registration registration;


}

@Embeddable
@Data
class JobDuration {
    @Column(name = "start_year")
    private int startYear;

    @Column(name = "end_year")
    private int endYear;
}



