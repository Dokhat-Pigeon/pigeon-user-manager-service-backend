package com.pigeon.usermanager.service.impl;

import com.pigeon.usermanager.exception.NotFoundException;
import com.pigeon.usermanager.mapper.TokenMapper;
import com.pigeon.usermanager.model.cache.TokenCache;
import com.pigeon.usermanager.model.dto.RefreshTokenDto;
import com.pigeon.usermanager.model.dto.TokenDto;
import com.pigeon.usermanager.model.entity.UserEntity;
import com.pigeon.usermanager.repository.UserRepository;
import com.pigeon.usermanager.repository.cache.TokenCacheRepository;
import com.pigeon.usermanager.security.JwtProvider;
import com.pigeon.usermanager.service.TokenService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtProvider tokenProvider;
    private final TokenCacheRepository tokenCacheRepository;
    private final TokenMapper tokenMapper;
    private final UserRepository userRepository;

    @Override
    public TokenDto getAuthToken() {
        HttpSession session1 = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
        String refreshToken = (String) session1.getAttribute("refreshToken");
        if (!tokenProvider.validateRefreshToken(refreshToken)) {
            return TokenDto.builder().build();
        }
        final Claims claims = tokenProvider.getRefreshClaims(refreshToken);
        final String login = claims.getSubject();
        final Optional<TokenCache> saveRefreshToken = tokenCacheRepository.findTokenCacheByLogin(login);
        if (saveRefreshToken.isEmpty() || !saveRefreshToken.get().getRefresh().equals(refreshToken)) {
            return TokenDto.builder().build();
        }
        final UserEntity user = userRepository.findByLogin(login)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        final String accessToken = tokenProvider.generateAccessToken(user);
        TokenDto build = TokenDto.builder()
                .authorization(accessToken)
                .refresh(refreshToken)
                .build();
        session1.setAttribute("token", build);
        return (TokenDto) session1.getAttribute("token");
    }

    @Override
    public TokenDto updateAuthToken(RefreshTokenDto refreshTokenDto) {
        String refreshToken = refreshTokenDto.getRefreshToken();
        if (!tokenProvider.validateRefreshToken(refreshToken)) {
            throw new NotFoundException("Невалидный JWT токен");
        }
        final Claims claims = tokenProvider.getRefreshClaims(refreshToken);
        final String login = claims.getSubject();
        final Optional<TokenCache> optionalToken = tokenCacheRepository.findTokenCacheByLogin(login);
        if (optionalToken.isEmpty() || !optionalToken.get().getRefresh().equals(refreshToken)) {
            throw new NotFoundException("Невалидный JWT токен");
        }
        final UserEntity user = userRepository.findByLogin(login)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        TokenCache token = this.generateTokens(user);
        TokenCache savedToken = optionalToken.get();
        savedToken.setAuthorization(token.getAuthorization());
        savedToken.setRefresh(token.getRefresh());
        tokenCacheRepository.save(savedToken);
        return tokenMapper.toDto(token);
    }

    @Override
    public TokenDto createAuthToken(UserEntity user) {
        TokenCache token = this.generateTokens(user);
        HttpSession session = this.getSession();
        tokenCacheRepository.save(token);
        TokenDto dto = tokenMapper.toDto(token);
        session.setAttribute("token", dto);
        return dto;
    }

    private TokenCache generateTokens(UserEntity user) {
        final String accessToken = tokenProvider.generateAccessToken(user);
        final String refreshToken = tokenProvider.generateRefreshToken(user);
        return TokenCache.builder()
                .login(user.getLogin())
                .authorization(accessToken)
                .refresh(refreshToken)
                .build();
    }

    private HttpSession getSession() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        return ((ServletRequestAttributes) attributes).getRequest().getSession();
    }
}
