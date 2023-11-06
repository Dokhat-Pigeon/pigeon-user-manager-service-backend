package com.pigeon.usermanager.service;

import com.pigeon.usermanager.model.dto.AuthorizationDto;
import com.pigeon.usermanager.model.dto.RegistrationDto;
import com.pigeon.usermanager.model.dto.TokenDto;

/**
 * User management service
 */
public interface UserService {

    /**
     * User registration
     *
     * @param registrationDto Registration form data
     */
    void register(RegistrationDto registrationDto);

    /**
     * User verification
     *
     * @param uuid Email verification record uuid
     * @return {@link TokenDto}
     */
    TokenDto verify(String uuid);

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
