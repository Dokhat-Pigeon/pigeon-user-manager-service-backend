package com.pigeon.usermanager.service;

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
    TokenDto getTokens();

    /**
     * Update auth token
     *
     * @return {@link TokenDto}
     */
    TokenDto updateAuthToken();

    /**
     * Create auth and refresh tokens
     *
     * @return {@link TokenDto}
     */
    TokenDto createAuthToken(UserEntity user);

    /**
     * Remove token from session
     *
     * @return {@link UserEntity}
     */
    UserEntity removeToken();
}
