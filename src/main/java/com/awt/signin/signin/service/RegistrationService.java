package com.awt.signin.signin.service;

import com.awt.signin.signin.entity.Registration;
import org.springframework.http.ResponseEntity;


public interface RegistrationService {
    ResponseEntity<?> saveUser(Registration registration);

    ResponseEntity<?> confirmEmail(String confirmationToken);
}
