package com.awt.signin.signin.controller;


import com.awt.signin.signin.entity.Registration;
import com.awt.signin.signin.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Registration registration) {
        return registrationService.saveUser(registration);
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {
        return registrationService.confirmEmail(confirmationToken);
    }

}