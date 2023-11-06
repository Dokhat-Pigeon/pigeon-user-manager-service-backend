package com.pigeon.usermanager.service.impl;

import com.pigeon.usermanager.service.ChangePasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangePasswordServiceImpl implements ChangePasswordService {

    @Override
    public void sendEmail(String email) {
        // TODO
    }

    @Override
    public void verify(String uuid) {
        // TODO
    }

    @Override
    public void confirm(String uuid) {
        // TODO
    }
}
