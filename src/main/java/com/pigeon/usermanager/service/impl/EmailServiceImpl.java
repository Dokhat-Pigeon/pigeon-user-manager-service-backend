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
        Map<String, Object> context = Map.of(
                EmailConstraints.USERNAME.getLabel(), registration.getName(),
                EmailConstraints.VERIFICATION_URL.getLabel(), url
        );
        emailSender.sendMail(this.createContext(registration.getEmail(), Event.VERIFICATION, context));
    }

    @Override
    public void sendChangePassword(ChangePasswordCache changePassword) {
        String url = confirmationUrl + "/" + changePassword.getRecordId();
        Map<String, Object> context = Map.of(
                EmailConstraints.USERNAME.getLabel(), changePassword.getUserName(),
                EmailConstraints.VERIFICATION_URL.getLabel(), url
        );
        emailSender.sendMail(this.createContext(changePassword.getUserEmail(), Event.CHANGE_PASSWORD, context));
    }

    @Override
    public void sendCompleteChangePassword(String email, String userName) {
        Map<String, Object> context = Map.of(
                EmailConstraints.USERNAME.getLabel(), userName
        );
        emailSender.sendMail(this.createContext(email, Event.COMPLETE_CHANGE_PASSWORD, context));
    }

    private EmailContext createContext(String email, Event event, Map<String, Object> contextVariables) {
        return EmailContext.builder()
                .from(serviceEmail)
                .to(email)
                .subject(event.getSubject())
                .templateLocation(event.getTemplateLocation())
                .context(contextVariables)
                .build();
    }

    @Getter
    @RequiredArgsConstructor
    private enum Event {

        VERIFICATION("Pigeon Регистрация", "VerificationEmail.html"),
        CHANGE_PASSWORD("Pigeon Смена пароля", "ChangePasswordEmail.html"),
        COMPLETE_CHANGE_PASSWORD("Pigeon Успешная смена пароля", "CompleteChangePasswordEmail.html");

        private final String subject;
        private final String templateLocation;
    }
}
