package com.pigeon.usermanager.service.impl;

import com.pigeon.usermanager.component.TokenProvider;
import com.pigeon.usermanager.model.dto.RefreshTokenDto;
import com.pigeon.usermanager.model.dto.TokenDto;
import com.pigeon.usermanager.model.entity.UserEntity;
import com.pigeon.usermanager.service.TokenService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenProvider tokenProvider;
    private final Map<String, String> refreshStorage = new HashMap<>();

    @Override
    public TokenDto getAuthToken() {
        if (tokenProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = tokenProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getByLogin(login)
                        .orElseThrow(() -> new AuthException("Пользователь не найден"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    @Override
    public TokenDto updateAuthToken(RefreshTokenDto refreshTokenDto) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getByLogin(login)
                        .orElseThrow(() -> new AuthException("Пользователь не найден"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getLogin(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Невалидный JWT токен");
    }

    @Override
    public TokenDto generateTokens(UserEntity user) {
        final String accessToken = tokenProvider.generateAccessToken(user);
        final String refreshToken = tokenProvider.generateRefreshToken(user);
        refreshStorage.put(user.getLogin(), refreshToken);
        return TokenDto.builder()
                .authorization(accessToken)
                .refresh(refreshToken)
                .build();
    }
}
