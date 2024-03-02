package com.pigeon.usermanager.security;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtParser {

    private final JwtProvider tokenProvider;

    public Authentication parse(String token) {
        JwtAuthentication authentication;
        if (token != null && tokenProvider.validateAccessToken(token)) {
            Claims claims = tokenProvider.getAccessClaims(token);
            authentication = JwtUtils.generate(claims);
        }
        else {
            authentication = JwtUtils.generateAnonymous();
        }
        authentication.setAuthenticated(true);
        return authentication;
    }
}
