package com.pigeon.usermanager.service.impl;

import com.pigeon.usermanager.model.cache.RegistrationCache;
import com.pigeon.usermanager.model.cache.UserCache;
import com.pigeon.usermanager.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendVerification(RegistrationCache registration) {

    }

    @Override
    public void sendChangePassword(UserCache user) {

    }
}
