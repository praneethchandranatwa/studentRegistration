package com.awt.signin.signin.service;


import com.awt.signin.signin.entity.ConfirmationToken;
import com.awt.signin.signin.entity.Registration;
import com.awt.signin.signin.repository.ConfirmationTokenRepository;
import com.awt.signin.signin.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


@Service
public class RegistrationServiceImplementation implements RegistrationService {


    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    EmailServiceImplementation emailServiceImplementation;

    @Override
    public ResponseEntity<?> saveUser(Registration registration) {

        if (registrationRepository.existsByUserEmail(registration.getUserEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        registrationRepository.save(registration);

        ConfirmationToken confirmationToken = new ConfirmationToken(registration);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(registration.getUserEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                + "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken());
        emailServiceImplementation.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());

        return ResponseEntity.ok("Verify email by the link sent on your email address");
    }

    @Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            Registration registration = registrationRepository.findByUserEmailIgnoreCase(token.getUserEntity().getUserEmail());
            registration.setEnabled(true);
            registrationRepository.save(registration);
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }
}

