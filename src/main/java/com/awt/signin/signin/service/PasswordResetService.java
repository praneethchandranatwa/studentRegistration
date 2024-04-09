package com.awt.signin.signin.service;

public interface PasswordResetService {
    String resetPassword(long id, String oldPassword, String newPassword);
}
