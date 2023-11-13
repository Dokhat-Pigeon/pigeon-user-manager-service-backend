package com.pigeon.usermanager.service;

import com.pigeon.usermanager.model.cache.RegistrationCache;
import com.pigeon.usermanager.model.cache.UserCache;

public interface EmailService {

    void sendVerification(RegistrationCache registration);

    void sendChangePassword(UserCache user);
}
