package com.pigeon.usermanager.service;

import com.pigeon.usermanager.model.cache.ChangePasswordCache;
import com.pigeon.usermanager.model.cache.RegistrationCache;
import com.pigeon.usermanager.model.cache.UserCache;

public interface EmailService {

    /**
     *  Sending verification letter for end registration
     * @param registration {@link RegistrationCache} Registration information
     */
    void sendVerification(RegistrationCache registration);

    /**
     *  Sending confirmation letter for change password
     * @param changePassword {@link UserCache} Change password information
     */
    void sendChangePassword(ChangePasswordCache changePassword);

    /**
     *  Sending letter confirming successful password change
     * @param email User email
     * @param userName User Name
     */
    void sendCompleteChangePassword(String email, String userName);
}
