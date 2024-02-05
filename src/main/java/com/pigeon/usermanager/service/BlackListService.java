package com.pigeon.usermanager.service;

import com.pigeon.usermanager.model.entity.UserEntity;

import java.util.List;

/**
 * Service for working with a blacklist
 */
public interface BlackListService {

    /**
     * Add user to blacklist
     *
     * @param id User identifier
     */
    void addUser(Long id);

    /**
     * Delete user from blacklist
     *
     * @param id User identifier
     */
    void deleteUser(Long id);

    /**
     * Delete user from blacklist
     *
     * @param page Current page
     * @return {@link List<UserEntity>}
     */
    List<UserEntity> getUsersFromBlacklist(Integer page);
}
