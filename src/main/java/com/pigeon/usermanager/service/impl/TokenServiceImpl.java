package com.pigeon.usermanager.service.impl;

import com.pigeon.usermanager.model.dto.RefreshTokenDto;
import com.pigeon.usermanager.model.dto.TokenDto;
import com.pigeon.usermanager.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    @Override
    public TokenDto getAuthToken() {
        // TODO
        return TokenDto.builder().build();
    }

    @Override
    public TokenDto updateAuthToken(RefreshTokenDto refreshTokenDto) {
        // TODO
        return TokenDto.builder().build();
    }
}
