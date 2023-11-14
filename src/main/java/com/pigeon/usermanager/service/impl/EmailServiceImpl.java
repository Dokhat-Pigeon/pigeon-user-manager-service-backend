package com.pigeon.usermanager.service.impl;

import com.pigeon.usermanager.component.EmailSender;
import com.pigeon.usermanager.model.cache.ChangePasswordCache;
import com.pigeon.usermanager.model.cache.RegistrationCache;
import com.pigeon.usermanager.model.context.EmailContext;
import com.pigeon.usermanager.model.enums.EmailConstraints;
import com.pigeon.usermanager.service.EmailService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final EmailSender emailSender;

    @Value("${spring.mail.username}")
    private String serviceEmail;
    @Value("${service.user.verification.url}")
    private String verificationUrl;
    @Value("${service.change-password.confirmation.url}")
    private String confirmationUrl;

    @Override
    public void sendVerification(RegistrationCache registration) {
        String url = verificationUrl + "/" + registration.getRecordId();
        emailSender.sendMail(this.createContext(registration.getEmail(), registration.getName(),
                url, Event.VERIFICATION));
    }

    @Override
    public void sendChangePassword(ChangePasswordCache changePassword) {
        String url = confirmationUrl + "/" + changePassword.getRecordId();
        emailSender.sendMail(this.createContext(changePassword.getUserEmail(), changePassword.getUserName(),
                url, Event.CHANGE_PASSWORD));
    }

    private EmailContext createContext(String email, String userName, String url, Event event) {
        return EmailContext.builder()
                .from(serviceEmail)
                .to(email)
                .subject(event.getSubject())
                .templateLocation(event.getTemplateLocation())
                .context(Map.of(
                        EmailConstraints.USERNAME.getLabel(), userName,
                        EmailConstraints.VERIFICATION_URL.getLabel(), url
                ))
                .build();
    }

    @Getter
    @RequiredArgsConstructor
    private enum Event {

        VERIFICATION("Pigeon Регистрация", "VerificationEmail.html"),
        CHANGE_PASSWORD("Pigeon Смена пароля", "ChangePasswordEmail.html");

        private final String subject;
        private final String templateLocation;
    }
}
