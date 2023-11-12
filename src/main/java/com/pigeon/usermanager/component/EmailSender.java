package com.pigeon.usermanager.component;

import com.pigeon.usermanager.model.context.EmailContext;

import javax.mail.MessagingException;

public interface EmailSender {

    void sendMail(EmailContext email) throws MessagingException;
}
