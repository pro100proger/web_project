package com.microservice.eurekaclient.service.implementation;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.microservice.eurekaclient.service.EmailSenderService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailSenderService {
    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email) throws SendFailedException {
        try {
            log.info(String.format("Service: sending email message to %s", to));
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setText(email, true);
            helper.setTo(to);
            helper.setFrom("permutationsproject@gmail.com", "Permutations Project");
            helper.setSubject("Welcome to Permutations Project");

            mailSender.send(mimeMessage);
        } catch (UnsupportedEncodingException | MessagingException e) {
            log.error(String.format("Service: failed to send email message to %s", to));
            throw new SendFailedException(String.format("Service: failed to send email message to %s", to));
        }
    }
}
