package com.awt.signin.signin.controller;

import com.awt.signin.signin.entity.ResetPasswordInputs;
import com.awt.signin.signin.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    @Autowired
    public PasswordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordInputs request) {
        String result = passwordResetService.resetPassword(request.getId(), request.getOldPassword(), request.getNewPassword());

        if (result.equals("Invalid old password") || result.equals("Same old and new password")) {
            return ResponseEntity.badRequest().body(result);
        } else if (result.equals("Password reset successful")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
