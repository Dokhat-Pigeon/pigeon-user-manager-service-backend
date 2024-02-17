package com.pigeon.usermanager.service.impl;

import com.pigeon.usermanager.exception.http.TokenServiceException;
import com.pigeon.usermanager.exception.http.UserServiceException;
import com.pigeon.usermanager.exception.enums.http.TokenErrorCode;
import com.pigeon.usermanager.exception.enums.http.UserErrorCode;
import com.pigeon.usermanager.model.dto.TokenDto;
import com.pigeon.usermanager.model.entity.UserEntity;
import com.pigeon.usermanager.repository.UserRepository;
import com.pigeon.usermanager.security.JwtProvider;
import com.pigeon.usermanager.service.TokenService;
import com.pigeon.usermanager.utils.SessionProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final String TOKEN_KEY = "token";

    private final JwtProvider tokenProvider;
    private final UserRepository userRepository;
    private final SessionProvider sessionProvider;

    @Override
    public TokenDto getTokens() {
        Object tokens = this.sessionProvider.getSession().getAttribute(TOKEN_KEY);

        if (tokens == null) throw this.generateException(TokenErrorCode.TOKEN_NOT_FOUND);
        else return (TokenDto) tokens;
    }

    @Override
    public TokenDto updateAuthToken() {
        TokenDto tokens = this.getTokens();
        UserEntity user = this.getUserFromRefresh(tokens.getRefresh());
        tokens.setAuthorization(tokenProvider.generateAccessToken(user));

        this.sessionProvider.getSession().setAttribute(TOKEN_KEY, tokens);
        return tokens;
    }

    @Override
    public TokenDto createAuthToken(UserEntity user) {
        TokenDto tokens = TokenDto.builder()
                .authorization(tokenProvider.generateAccessToken(user))
                .refresh(tokenProvider.generateRefreshToken(user))
                .build();
        this.sessionProvider.getSession().setAttribute(TOKEN_KEY, tokens);
        return tokens;
    }

    @Override
    public UserEntity removeToken() {
        TokenDto token = this.getTokens();
        UserEntity user = this.getUserFromRefresh(token.getRefresh());
        this.sessionProvider.getSession().removeAttribute(TOKEN_KEY);
        return user;
    }

    private UserEntity getUserFromRefresh(String refreshToken) {
        if (!tokenProvider.validateRefreshToken(refreshToken)) {
            throw this.generateException(TokenErrorCode.INVALID_REFRESH_TOKEN);
        }
        Claims claims = tokenProvider.getRefreshClaims(refreshToken);
        String login = claims.getSubject();
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UserServiceException(UserErrorCode.USER_NOT_FOUND));
    }

    private TokenServiceException generateException(TokenErrorCode errorCode) {
        return new TokenServiceException(errorCode);
    }
}
