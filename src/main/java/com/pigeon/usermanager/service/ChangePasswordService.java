package com.pigeon.usermanager.service;

import com.pigeon.usermanager.model.dto.ChangePasswordDto;

import java.util.UUID;

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
    void verify(UUID uuid);

    /**
     * Confirmation of changing the password
     *
     * @param changePassword Change password form data
     */
    void confirm(ChangePasswordDto changePassword);
}
