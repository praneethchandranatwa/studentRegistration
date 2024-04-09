package com.awt.signin.signin.service;

import com.awt.signin.signin.entity.Registration;
import com.awt.signin.signin.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImplementation implements ProfileService {

    private final RegistrationRepository registrationRepository;

    @Autowired
    public ProfileServiceImplementation(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public List<Registration> getProfileDetails() {
        return registrationRepository.findAll();
    }
}
