package com.pigeon.usermanager.service.impl;

import com.pigeon.usermanager.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {



    @Override
    public void sendVerification() {

    }

    @Override
    public void sendChangePassword(String email) {

    }
}
