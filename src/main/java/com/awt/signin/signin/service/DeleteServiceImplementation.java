package com.awt.signin.signin.service;

import com.awt.signin.signin.entity.Registration;
import com.awt.signin.signin.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteServiceImplementation implements DeleteService {

    private final RegistrationRepository registrationRepository;

    @Autowired
    public DeleteServiceImplementation(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public String deleteProfile(Long id) {
        Optional<Registration> repositoryById = registrationRepository.findById(id);

        if (repositoryById.isPresent()) {
            registrationRepository.deleteById(id);
            return "Profile with ID " + id + " is deleted successfully";
        } else {
            return "Profile with ID " + id + " not found";
        }
    }
}
