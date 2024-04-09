package com.awt.signin.signin.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    public void sendEmail(SimpleMailMessage email);
}
