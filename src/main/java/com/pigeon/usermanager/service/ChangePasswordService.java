package com.pigeon.usermanager.service;

/**
 * Service for changing the password of an unauthorized user
 */
public interface ChangePasswordService {

    /**
     * Send confirmation email
     *
     * @param email User email
     */
    void sendEmail(String email);

    /**
     * Verifying the possibility of changing the password
     *
     * @param uuid Change password record uuid
     */
    void verify(String uuid);

    /**
     * Confirmation of changing the password
     *
     * @param uuid Change password record uuid
     */
    void confirm(String uuid);
}
