package com.awt.signin.signin.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "confirmationToken")
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "token_id")
    private Long tokenId;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = Registration.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Registration registration;

    public ConfirmationToken() {
    }

    public ConfirmationToken(Registration registration) {
        this.registration = registration;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

    public Long getTokenId() {
        return tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Registration getUserEntity() {
        return registration;
    }

    public void setUserEntity(Registration registration) {
        this.registration = registration;
    }


}