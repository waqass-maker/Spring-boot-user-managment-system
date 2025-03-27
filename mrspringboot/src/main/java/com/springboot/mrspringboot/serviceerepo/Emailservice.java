package com.springboot.mrspringboot.serviceerepo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Emailservice {

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(String to, String subject, String body) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(body);
            javaMailSender.send(mail);

            log.info("✅ Email sent successfully to {}", to);
        } catch (Exception e) {
            log.error("❌ Error while sending email: {}", e.getMessage());
        }
    }
}
