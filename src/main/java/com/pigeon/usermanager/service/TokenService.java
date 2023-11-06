package com.pigeon.usermanager.service;

import com.pigeon.usermanager.model.dto.RefreshTokenDto;
import com.pigeon.usermanager.model.dto.TokenDto;

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
}
