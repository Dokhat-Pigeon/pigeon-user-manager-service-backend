package com.pigeon.usermanager.service;

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
     * @param uid User unique identifier
     */
    void verify(String uid);

    /**
     * Confirmation of changing the password
     *
     * @param uid User unique identifier
     */
    void confirm(String uid);
}
