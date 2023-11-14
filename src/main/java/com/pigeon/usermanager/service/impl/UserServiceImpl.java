package com.pigeon.usermanager.service.impl;

import com.pigeon.usermanager.exception.NotFoundException;
import com.pigeon.usermanager.exception.WrongPasswordException;
import com.pigeon.usermanager.model.dto.AuthorizationDto;
import com.pigeon.usermanager.model.dto.RegistrationDto;
import com.pigeon.usermanager.model.dto.TokenDto;
import com.pigeon.usermanager.model.entity.UserEntity;
import com.pigeon.usermanager.repository.UserRepository;
import com.pigeon.usermanager.service.TokenService;
import com.pigeon.usermanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Override
    public void register(RegistrationDto registrationDto) {
        // TODO
    }

    @Override
    public TokenDto verify(UUID uuid) {
        // TODO
        return TokenDto.builder().build();
    }

    @Override
    public TokenDto login(AuthorizationDto authorizationDto) {
        final UserEntity user = this.getByLoginOrEmail(authorizationDto.getLoginOrEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));
        if (user.getPassword().equals(authorizationDto.getPassword())) {
            return tokenService.generateTokens(user);
        } else {
            throw new WrongPasswordException("Wrong password");
        }
    }

    @Override
    public void logout() {
        // TODO
    }

    public Optional<UserEntity> getByLoginOrEmail(String loginOrEmail)
    {
        return userRepository.findByLoginOrEmail(loginOrEmail, loginOrEmail);
    }
}
