package com.pigeon.usermanager.service;

import com.pigeon.usermanager.model.dto.AuthorizationDto;
import com.pigeon.usermanager.model.dto.RegistrationDto;
import com.pigeon.usermanager.model.dto.TokenDto;

import javax.security.auth.message.AuthException;
import java.util.UUID;

/**
 * User management service
 */
public interface UserService {

    /**
     * User registration
     *
     * @param registration Registration form data
     */
    void register(RegistrationDto registration);

    /**
     * User verification
     *
     * @param uuid Email verification record uuid
     * @return {@link TokenDto}
     */
    TokenDto verify(UUID uuid);

    /**
     * User authorization
     *
     * @param authorizationDto Authorization form data
     * @return {@link TokenDto}
     */
    TokenDto login(AuthorizationDto authorizationDto);

    /**
     * User logout
     */
    void logout();
}
