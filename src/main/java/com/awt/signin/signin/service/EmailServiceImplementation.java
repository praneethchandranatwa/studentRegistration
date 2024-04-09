package com.awt.signin.signin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailServiceImplementation implements EmailService{


    private JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImplementation(JavaMailSender javaMailSender) {

        this.javaMailSender = javaMailSender;
    }

   @Async
   @Override
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }



}
