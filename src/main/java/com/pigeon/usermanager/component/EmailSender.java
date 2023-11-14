package com.pigeon.usermanager.component;

import com.pigeon.usermanager.model.context.EmailContext;

public interface EmailSender {

    /**
     * Sending email letter
     * @param email {@link EmailContext} Context with information for letter
     */

    void sendMail(EmailContext email);
}
