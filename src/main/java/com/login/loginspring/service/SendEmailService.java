package com.login.loginspring.service;
import com.login.loginspring.domain.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SendEmailService {
    private final JavaMailSender javaMailSender;

    public void send(Email email) {
        log.info("Sending simple email");

        var message = new SimpleMailMessage();
        message.setTo(email.getDestiny());

        message.setSubject(email.getTitle());
        message.setText(email.getBody());
        javaMailSender.send(message);
        log.info("Email successfully sent!");
    }

    /*public void sendWithAttachment(Email email, String file) throws MessagingException {
        log.info("Sending email with attachment.");
        var message = javaMailSender.createMimeMessage();

        var helper = new MimeMessageHelper(message, true);

        helper.setTo(email.getDestiny());
        helper.setSubject(email.getTitle());
        helper.setText(email.getBody(), true);

        helper.addAttachment("image1.jpeg", new ClassPathResource(file));

        javaMailSender.send(message);
        log.info("Email with attachment sent successfully!");
    }*/
}