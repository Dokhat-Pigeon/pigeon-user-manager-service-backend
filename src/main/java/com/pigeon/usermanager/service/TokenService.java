package com.pigeon.usermanager.service;

import com.pigeon.usermanager.model.dto.RefreshTokenDto;
import com.pigeon.usermanager.model.dto.TokenDto;
import com.pigeon.usermanager.model.entity.UserEntity;

import javax.servlet.http.HttpSession;

/**
 * Service for working with the authentication token
 */
public interface TokenService {

    /**
     * Get auth token
     *
     * @return {@link TokenDto}
     */
    TokenDto getAuthToken(HttpSession session);

    /**
     * Update auth token
     *
     * @return {@link TokenDto}
     */
    TokenDto updateAuthToken(RefreshTokenDto  refreshTokenDto);

    /**
     * Create auth and refresh tokens
     *
     * @return {@link TokenDto}
     */
    TokenDto createAuthToken(UserEntity user);
}
