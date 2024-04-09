package com.awt.signin.signin.controller;

import com.awt.signin.signin.entity.Registration;
import com.awt.signin.signin.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    public List<Registration> getProfileDetails() {
        return profileService.getProfileDetails();
    }
}
