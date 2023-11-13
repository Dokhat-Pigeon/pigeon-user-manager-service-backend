package com.pigeon.usermanager.service.impl;

import com.pigeon.usermanager.component.EmailSender;
import com.pigeon.usermanager.model.cache.RegistrationCache;
import com.pigeon.usermanager.model.cache.UserCache;
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

    @Override
    public void sendVerification(RegistrationCache registration) {
        String url = verificationUrl + "/" + registration.getRecordId();

        EmailContext emailContext = EmailContext.builder()
                .from(serviceEmail)
                .to(registration.getEmail())
                .subject(Event.VERIFICATION.getSubject())
                .templateLocation(Event.VERIFICATION.getTemplateLocation())
                .context(Map.of(
                        EmailConstraints.USERNAME.getLabel(), registration.getName(),
                        EmailConstraints.VERIFICATION_URL.getLabel(), url
                ))
                .build();
        emailSender.sendMail(emailContext);
    }

    @Override
    public void sendChangePassword(UserCache user) {

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
