package com.example.billetms.Batch.Configuration;

import com.example.billetms.entities.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class EmailConfig {

@Autowired
JavaMailSender javaMailSender;

    public void sendEmailwithoutExtension(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        message.setFrom("p7senderOCR@gmail.com");
        message.setTo(email.getEmailClient());
        message.setSubject("Votre emprunt arrive à échéance");
        message.setText("Bonjour " + email.getNom() + " " + email.getPrenom() + ", votre emprunt du livre\" " + email.getBookTitle() + "\" est arrivé à terme le " + dateFormat.format(email.getEndDate()) + ".");
        System.out.println(message);

        javaMailSender.send(message);
    }

    public void sendEmailwithExtension(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        message.setFrom("p7senderOCR@gmail.com");
        message.setTo(email.getEmailClient());
        message.setSubject("Votre emprunt arrive à échéance");
        message.setText("Bonjour " + email.getNom() + " " + email.getPrenom() + ", votre emprunt du livre\" " + email.getBookTitle() + "\" est arrivé à terme le " + dateFormat.format(email.getEndDate()) + ".");

        javaMailSender.send(message);
    }
}
