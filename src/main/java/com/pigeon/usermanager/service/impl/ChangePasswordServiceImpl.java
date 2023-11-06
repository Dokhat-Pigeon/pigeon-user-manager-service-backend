package com.pigeon.usermanager.service.impl;

import com.pigeon.usermanager.service.ChangePasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChangePasswordServiceImpl implements ChangePasswordService {

    @Override
    public void sendEmail(String email) {
        // TODO
    }

    @Override
    public void verify(UUID uuid) {
        // TODO
    }

    @Override
    public void confirm(UUID uuid) {
        // TODO
    }
}
