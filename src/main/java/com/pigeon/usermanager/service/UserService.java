package com.pigeon.usermanager.service;

import com.pigeon.usermanager.model.dto.AuthorizationDto;
import com.pigeon.usermanager.model.dto.RegistrationDto;
import com.pigeon.usermanager.model.dto.TokenDto;
import com.pigeon.usermanager.model.dto.UserDto;
import com.pigeon.usermanager.model.entity.UserEntity;

import java.security.Principal;
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
     *
     * @return {@link UserEntity}
     */
    UserEntity logout();

    /**
     * Update online status
     *
     * @param principal Security principal
     * @param isOnline Online flag
     * @return Status been changed
     */
    boolean updateOnlineStatus(Principal principal, boolean isOnline);

    /**
     * Get user by login
     * @param login User login
     * @return {@link UserDto}
     */
    UserDto getDtoByLogin(String login);

    /**
     * Get user DTO from entity
     * @param user User entity
     * @return {@link UserDto}
     */
    UserDto getDtoByEntity(UserEntity user);

    /**
     * Get user entity by ID
     *
     * @param id User ID
     * @return {@link UserEntity}
     */
    UserEntity getUserById(Long id);

    /**
     * @return {@link UserEntity}
     */
    UserEntity getCurrentUser();
}
