package com.pigeon.usermanager.service;

import com.pigeon.usermanager.model.dto.RefreshTokenDto;
import com.pigeon.usermanager.model.dto.TokenDto;
import com.pigeon.usermanager.model.entity.UserEntity;

/**
 * Service for working with the authentication token
 */
public interface TokenService {

    /**
     * Get auth token
     *
     * @return {@link TokenDto}
     */
    TokenDto getAuthToken();

    /**
     * Update auth token
     *
     * @return {@link TokenDto}
     */
    TokenDto updateAuthToken(RefreshTokenDto  refreshTokenDto);

    /**
     * Generate auth and refresh tokens
     *
     * @return {@link TokenDto}
     */
    TokenDto generateTokens(UserEntity user);
}
