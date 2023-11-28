package com.pigeon.usermanager.service.impl;

import com.pigeon.usermanager.exception.UserServiceException;
import com.pigeon.usermanager.exception.enums.UserErrorCode;
import com.pigeon.usermanager.mapper.UserMapper;
import com.pigeon.usermanager.model.cache.RegistrationCache;
import com.pigeon.usermanager.model.dto.AuthorizationDto;
import com.pigeon.usermanager.model.dto.RegistrationDto;
import com.pigeon.usermanager.model.dto.TokenDto;
import com.pigeon.usermanager.model.entity.UserEntity;
import com.pigeon.usermanager.repository.UserRepository;
import com.pigeon.usermanager.model.enums.UserRole;
import com.pigeon.usermanager.model.enums.UserStatus;
import com.pigeon.usermanager.repository.cache.RegistrationCacheRepository;
import com.pigeon.usermanager.service.EmailService;
import com.pigeon.usermanager.service.TokenService;
import com.pigeon.usermanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final UserMapper userMapper;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final RegistrationCacheRepository registrationCacheRepository;

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
        Optional<UserEntity> userEntity = this.endRegister(registrationCacheRepository.findById(uuid));
        if(userEntity.isEmpty()) this.generateException(UserErrorCode.WRONG_VERIFICATION_URL);

        registrationCacheRepository.deleteById(uuid);
        return tokenService.createAuthToken(userEntity.get());
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

    private void validationRegistration(RegistrationDto registration) {
        if (!EmailValidator.getInstance().isValid(registration.getEmail())) {
            this.generateException(UserErrorCode.WRONG_EMAIL_ADDRESS);
        } else if (this.isNotUniqueEmail(registration)) {
            this.generateException(UserErrorCode.EMAIL_ALREADY_USE);
        } else if (this.isNotUniqueLogin(registration)) {
            this.generateException(UserErrorCode.LOGIN_ALREADY_USE);
        } else if (!registration.getPassword().equals(registration.getConfirmPassword())) {
            this.generateException(UserErrorCode.DIFFERENT_CONFIRM_PASSWORD);
        }
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

    private Optional<UserEntity> endRegister(Optional<RegistrationCache> registration) {
        return registration.map(this::createUserEntity).map(userRepository::save);
    }

    private UserEntity createUserEntity(RegistrationCache registration) {
        return userMapper.toEntity(registration).toBuilder()
                .role(UserRole.USER)
                .build();
    }

    private UserEntity getByLoginOrEmail(String loginOrEmail) {
        return userRepository.findByLoginOrEmail(loginOrEmail, loginOrEmail)
                .orElseThrow(() -> this.createException(UserErrorCode.WRONG_EMAIL_OR_LOGIN));
    }

    private void generateException(UserErrorCode errorCode) throws UserServiceException {
        throw this.createException(errorCode);
    }

    private UserServiceException createException(UserErrorCode errorCode) {
        return new UserServiceException(errorCode, new Exception());
    }
}
