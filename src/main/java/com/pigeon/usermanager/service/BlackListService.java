package com.pigeon.usermanager.service;

public interface BlackListService {

    /**
     * Add user to blacklist
     *
     * @param uid User unique identifier
     */
    void add(String uid);

    /**
     * Delete user from blacklist
     *
     * @param uid User unique identifier
     */
    void delete(String uid);
}
