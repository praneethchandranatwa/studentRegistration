package com.awt.signin.signin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ReferenceLink {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long RefId;

    private String title;

    private String url;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "subSectionId")
    private SubSection subSection;

}
