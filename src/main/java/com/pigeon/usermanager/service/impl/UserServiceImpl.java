package com.pigeon.usermanager.service.impl;

import com.pigeon.usermanager.model.dto.AuthorizationDto;
import com.pigeon.usermanager.model.dto.RegistrationDto;
import com.pigeon.usermanager.model.dto.TokenDto;
import com.pigeon.usermanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

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
        // TODO
        return TokenDto.builder().build();
    }

    @Override
    public void logout() {
        // TODO
    }
}