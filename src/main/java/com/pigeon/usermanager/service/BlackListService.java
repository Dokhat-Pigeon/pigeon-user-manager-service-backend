package com.pigeon.usermanager.service;

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
}
