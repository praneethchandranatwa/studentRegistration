package com.awt.signin.signin.controller;

import com.awt.signin.signin.entity.Login;
import com.awt.signin.signin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) {
        String email = login.getEmail();
        String password = login.getPassword();

        String result = loginService.login(email, password);

        if (result.equals("Invalid email or password")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }
}
