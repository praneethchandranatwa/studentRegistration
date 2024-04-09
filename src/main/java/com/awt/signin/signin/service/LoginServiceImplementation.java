package com.awt.signin.signin.service;

import com.awt.signin.signin.entity.Registration;
import com.awt.signin.signin.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImplementation implements LoginService {

    private final RegistrationRepository registrationRepository;

    @Autowired
    public LoginServiceImplementation(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public String login(String email, String password) {
        Registration registration = registrationRepository.findByUserEmailIgnoreCase(email);

        if (registration == null || !registration.getUserPassword().equals(password)) {
            return "Invalid email or password";
        } else {
            return "Login successful";
        }
    }
}
