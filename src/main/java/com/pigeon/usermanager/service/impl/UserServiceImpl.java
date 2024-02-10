package com.pigeon.usermanager.service.impl;

import com.pigeon.usermanager.exception.enums.http.UserErrorCode;
import com.pigeon.usermanager.exception.http.UserServiceException;
import com.pigeon.usermanager.exception.runtime.UserOnlineServiceException;
import com.pigeon.usermanager.mapper.UserMapper;
import com.pigeon.usermanager.model.cache.RegistrationCache;
import com.pigeon.usermanager.model.dto.*;
import com.pigeon.usermanager.model.entity.UserEntity;
import com.pigeon.usermanager.model.enums.UserRole;
import com.pigeon.usermanager.model.enums.UserStatus;
import com.pigeon.usermanager.repository.UserRepository;
import com.pigeon.usermanager.repository.cache.RegistrationCacheRepository;
import com.pigeon.usermanager.security.JwtAuthentication;
import com.pigeon.usermanager.service.EmailService;
import com.pigeon.usermanager.service.TokenService;
import com.pigeon.usermanager.service.UserOnlineService;
import com.pigeon.usermanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.UUID;

import static com.pigeon.usermanager.exception.enums.http.UserErrorCode.*;
import static com.pigeon.usermanager.exception.enums.runtime.UserOnlineErrorCode.AUTH_PARSING_ERROR;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final UserMapper userMapper;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final RegistrationCacheRepository registrationCacheRepository;
    private final UserOnlineService userOnlineService;

    @Value("${cache.registration.verification.ttl}")
    private Long registrationTtl;

    @Override
    public void register(RegistrationDto registration) {
        this.validationRegistration(registration);

        registration.setPassword(passwordEncoder.encode(registration.getPassword()));
        RegistrationCache registrationCache = userMapper
                .toCache(registration, UUID.randomUUID(), UserStatus.ACTIVE, registrationTtl);

        registrationCacheRepository.save(registrationCache);
        emailService.sendVerification(registrationCache);
    }

    @Override
    public TokenDto verify(UUID uuid) {
        UserEntity user = this.endRegister(uuid);
        userOnlineService.create(user);
        return tokenService.createAuthToken(user);
    }

    @Override
    public TokenDto login(AuthorizationDto authorizationDto) {
        UserEntity user = this.getByLoginOrEmail(authorizationDto.getLoginOrEmail());
        if (passwordEncoder.matches(authorizationDto.getPassword(), user.getPassword())) {
            return tokenService.createAuthToken(user);
        }
        throw this.createException(UserErrorCode.WRONG_PASSWORD);
    }

    @Override
    public UserEntity logout() {
        return tokenService.removeToken();
    }

    @Override
    public boolean updateOnlineStatus(Principal principal, boolean isOnline) {
        if (principal instanceof JwtAuthentication auth) {
            UserEntity user = this.getByLoginOrEmail(auth.getUsername());
            return userOnlineService.update(user, isOnline);
        } else {
            throw new UserOnlineServiceException(AUTH_PARSING_ERROR);
        }
    }

    @Override
    public UserDto getByLogin(String login) {
        UserEntity user = userRepository.findByLogin(login).orElseThrow(() -> this.createException(USER_NOT_FOUND));
        UserOnlineDto userOnline = userOnlineService.get(user);
        return userMapper.toDto(user, userOnline);
    }

    private void validationRegistration(RegistrationDto registration) {
        UserErrorCode errorCode = null;
        if (!EmailValidator.getInstance().isValid(registration.getEmail())) errorCode = WRONG_EMAIL_ADDRESS;
        else if (this.isNotUniqueEmail(registration)) errorCode = EMAIL_ALREADY_USE;
        else if (this.isNotUniqueLogin(registration)) errorCode = LOGIN_ALREADY_USE;
        else if (this.checkConfirmPassword(registration)) errorCode = DIFFERENT_CONFIRM_PASSWORD;

        if (errorCode != null) this.generateException(errorCode);
    }

    private boolean isNotUniqueEmail(RegistrationDto registration) {
        String email = registration.getEmail();
        return userRepository.findByEmail(email).isPresent()
                || registrationCacheRepository.findByEmail(email).isPresent();
    }

    private boolean isNotUniqueLogin(RegistrationDto registration) {
        String login = registration.getLogin();
        return userRepository.findByLogin(login).isPresent()
                || registrationCacheRepository.findByLogin(login).isPresent();
    }

    private UserEntity endRegister(UUID uuid) {
        UserEntity user = registrationCacheRepository.findById(uuid)
                .map(this::createUserEntity)
                .map(userRepository::save)
                .orElseThrow(() -> this.createException(WRONG_VERIFICATION_URL));
        registrationCacheRepository.deleteById(uuid);
        return user;
    }

    private UserEntity createUserEntity(RegistrationCache registration) {
        return userMapper.toEntity(registration).toBuilder()
                .role(UserRole.USER)
                .build();
    }

    private UserEntity getByLoginOrEmail(String loginOrEmail) {
        return userRepository.findByLoginOrEmail(loginOrEmail, loginOrEmail)
                .orElseThrow(() -> this.createException(WRONG_EMAIL_OR_LOGIN));
    }

    private boolean checkConfirmPassword(RegistrationDto registration) {
        return !registration.getPassword().equals(registration.getConfirmPassword());
    }

    private void generateException(UserErrorCode errorCode) {
        throw this.createException(errorCode);
    }

    private UserServiceException createException(UserErrorCode errorCode) {
        return new UserServiceException(errorCode);
    }
}
