package com.login.loginspring.api;

import com.login.loginspring.domain.Email;
import com.login.loginspring.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;

@RestController
@RequestMapping("/apiemail")
@RequiredArgsConstructor
public class SendEmail {
    private final SendEmailService sendEmailService;

    @PostMapping("/send")
    public void sendEmail(@RequestBody Email email){
        sendEmailService.send(email);
    }

    /*@PostMapping("/sendWithAttachment")
    public void sendWithAttachment(@RequestBody Email email, String file) throws MessagingException {
        sendEmailService.sendWithAttachment(email, file);
    }*/
}
