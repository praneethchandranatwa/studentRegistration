package com.awt.signin.signin.service;

import com.awt.signin.signin.entity.Registration;
import com.awt.signin.signin.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordResetServiceImplementation implements PasswordResetService {

    private final RegistrationRepository registrationRepository;

    @Autowired
    public PasswordResetServiceImplementation(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public String resetPassword(long id, String oldPassword, String newPassword) {
        Optional<Registration> optionalRequest = registrationRepository.findById(id);

        if (optionalRequest.isPresent()) {
            Registration existingRequest = optionalRequest.get();

            if (!existingRequest.getUserPassword().equals(oldPassword)) {
                return "Invalid old password";
            } else if (newPassword.equals(oldPassword)) {
                return "Same old and new password";
            } else {
                existingRequest.setUserPassword(newPassword);
                registrationRepository.save(existingRequest);
                return "Password reset successful";
            }
        } else {
            return "Registration Not Found";
        }
    }
}
