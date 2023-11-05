package com.pigeon.usermanager.service;

import com.pigeon.usermanager.model.dto.RefreshTokenDto;
import com.pigeon.usermanager.model.dto.TokenDto;

public interface TokenService {

    /**
     * Get auth token
     *
     * @return {@link TokenDto}
     */
    TokenDto getAuthToken();

    /**
     * Get Refresh token
     *
     * @return {@link TokenDto}
     */
    TokenDto getRefreshToken(RefreshTokenDto  refreshTokenDto);
}
