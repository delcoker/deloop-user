package com.deloop.user.core.services.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Objects;


@Slf4j
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender emailSender;

    @Async
    public void send(String emailAddressTo, String composedEmail) {

        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(composedEmail, true);
            helper.setTo(emailAddressTo);
            helper.setSubject("Please confirm your email.");
            helper.setFrom(Objects.requireNonNull(((JavaMailSenderImpl) emailSender).getUsername()) + ": DELooP LTD.");
            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            String message = "Failed to send email";
            log.error(message, e);
            throw new IllegalStateException(message);
        }
    }
}