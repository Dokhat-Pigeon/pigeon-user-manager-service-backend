package com.pigeon.usermanager.service.impl;

import com.pigeon.usermanager.exception.http.ChangePasswordException;
import com.pigeon.usermanager.exception.enums.http.ChangePasswordError;
import com.pigeon.usermanager.exception.http.UserServiceException;
import com.pigeon.usermanager.model.cache.ChangePasswordCache;
import com.pigeon.usermanager.model.dto.ChangePasswordDto;
import com.pigeon.usermanager.model.entity.UserEntity;
import com.pigeon.usermanager.repository.cache.ChangePasswordCacheRepository;
import com.pigeon.usermanager.service.ChangePasswordService;
import com.pigeon.usermanager.service.EmailService;
import com.pigeon.usermanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.pigeon.usermanager.exception.enums.http.ChangePasswordError.*;

@Service
@RequiredArgsConstructor
public class ChangePasswordServiceImpl implements ChangePasswordService {

    private final EmailService emailService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ChangePasswordCacheRepository changePasswordRepository;

    @Value("${cache.change-password.confirmation.ttl}")
    private Long confirmationTtl;

    @Override
    public void sendEmail(String email) {
        ChangePasswordCache changePassword = this.prepareChangePassword(email);
        changePasswordRepository.save(changePassword);
        emailService.sendChangePassword(changePassword);
    }

    @Override
    public void verify(UUID uuid) {
        changePasswordRepository.findById(uuid).orElseThrow(() -> this.generateException(CONFIRMATION_ERROR));
    }

    @Override
    @Transactional
    public void confirm(ChangePasswordDto changePassword) {
        if (!changePassword.getNewPassword().equals(changePassword.getConfirmNewPassword())) {
            this.throwException(CONFIRM_PASSWORD_ERROR);
        }
        changePasswordRepository.findById(changePassword.getUuid()).ifPresentOrElse(
                cache -> {
                    this.changePassword(cache, changePassword);
                    emailService.sendCompleteChangePassword(cache.getUserEmail(), cache.getUserName());
                },
                () -> this.throwException(CONFIRMATION_ERROR)
        );
    }

    private ChangePasswordCache prepareChangePassword(String email) {
        try {
            return this.createChangePassword(userService.getByLoginOrEmail(email));
        } catch (UserServiceException e) {
            throw this.generateException(WRONG_EMAIL_ADDRESS, email);
        }
    }

    private ChangePasswordException generateException(ChangePasswordError error, Object... args) {
        return new ChangePasswordException(error, args);
    }

    private void throwException(ChangePasswordError error, Object... args) {
        throw this.generateException(error, args);
    }

    private ChangePasswordCache createChangePassword(UserEntity user) {
        return ChangePasswordCache.builder()
                .recordId(UUID.randomUUID())
                .userId(user.getId())
                .userName(user.getName())
                .userEmail(user.getEmail())
                .timeToLive(confirmationTtl)
                .build();
    }

    private void changePassword(ChangePasswordCache cache, ChangePasswordDto changePassword) {
        try {
            UserEntity user = userService.getUserById(cache.getUserId());
            user.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
            user.setState(UUID.randomUUID());
            changePasswordRepository.deleteById(cache.getRecordId());
        } catch (UserServiceException e) {
            throw this.generateException(CHANGE_PASSWORD_ERROR);
        }
    }
}
