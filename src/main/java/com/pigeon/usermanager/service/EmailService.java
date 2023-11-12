package com.pigeon.usermanager.service;

public interface EmailService {

    void sendVerification();

    void sendChangePassword(String email);
}
