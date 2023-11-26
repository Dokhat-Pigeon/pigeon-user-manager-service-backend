package com.pigeon.usermanager.service.impl;

import com.pigeon.usermanager.exception.TokenServiceException;
import com.pigeon.usermanager.exception.enums.TokenErrorCode;
import com.pigeon.usermanager.model.dto.TokenDto;
import com.pigeon.usermanager.model.entity.UserEntity;
import com.pigeon.usermanager.repository.UserRepository;
import com.pigeon.usermanager.security.JwtProvider;
import com.pigeon.usermanager.service.TokenService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final String TOKEN_KEY = "token";

    private final JwtProvider tokenProvider;
    private final UserRepository userRepository;
    private HttpSession session;

    @Override
    public TokenDto getAuthToken() {
        try {
            String refreshToken = this.getRefreshTokenFromSession();
            UserEntity user = this.getUserFromRefresh(refreshToken);
            String accessToken = tokenProvider.generateAccessToken(user);
            TokenDto token = this.buildToken(accessToken, refreshToken);
            this.updateTokensInSession(token);
            return token;
        } catch (TokenServiceException e) {
            return this.buildToken(null, null);
        }
    }

    @Override
    public TokenDto updateAuthToken() throws TokenServiceException {
        String refreshToken = this.getRefreshTokenFromSession();
        UserEntity user = this.getUserFromRefresh(refreshToken);
        return this.createAuthToken(user);
    }

    @Override
    public TokenDto createAuthToken(UserEntity user) {
        TokenDto token = this.generateTokens(user);
        this.updateTokensInSession(token);
        return token;
    }

    @Override
    public UserEntity removeToken() {
        TokenDto token = this.getTokenFromSession();
        if (token == null) {
            throw this.generateException(TokenErrorCode.TOKEN_NOT_FOUND);
        }
        UserEntity user = this.getUserFromRefresh(token.getRefresh());
        this.getSession().removeAttribute(TOKEN_KEY);
        return user;
    }

    private UserEntity getUserFromRefresh(String refreshToken) {
        if (!tokenProvider.validateRefreshToken(refreshToken)) {
            throw this.generateException(TokenErrorCode.INVALID_REFRESH_TOKEN);
        }
        Claims claims = tokenProvider.getRefreshClaims(refreshToken);
        String login = claims.getSubject();
        return userRepository.findByLogin(login)
                .orElseThrow(() -> this.generateException(TokenErrorCode.USER_NOT_FOUND));
    }

    private TokenDto generateTokens(UserEntity user) {
        String accessToken = tokenProvider.generateAccessToken(user);
        String refreshToken = tokenProvider.generateRefreshToken(user);
        return this.buildToken(accessToken, refreshToken);
    }

    private TokenDto buildToken(String accessToken, String refreshToken)
    {
        return TokenDto.builder()
                .authorization(accessToken)
                .refresh(refreshToken)
                .build();
    }

    private String getRefreshTokenFromSession() throws TokenServiceException{
        TokenDto token = this.getTokenFromSession();
        if (token != null) {
            return token.getRefresh();
        }
        throw this.generateException(TokenErrorCode.TOKEN_NOT_FOUND);
    }

    private void updateTokensInSession(TokenDto token) {
        HttpSession session = this.getSession();
        session.setAttribute(TOKEN_KEY, token);
    }

    private TokenDto getTokenFromSession() {
        HttpSession session = this.getSession();
        return (TokenDto) session.getAttribute(TOKEN_KEY);
    }

    private HttpSession getSession() {
        if (session != null) {
            return session;
        }
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        session = ((ServletRequestAttributes) attributes).getRequest().getSession();
        return session;
    }

    private TokenServiceException generateException(TokenErrorCode errorCode) {
        return new TokenServiceException(errorCode, new Exception());
    }
}
