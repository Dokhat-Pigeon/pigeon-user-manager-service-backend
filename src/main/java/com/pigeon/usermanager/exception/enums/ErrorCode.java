package com.pigeon.usermanager.exception.enums;

import java.text.MessageFormat;

public interface ErrorCode {

    String getMessageTemplate();

    default String getMessage(Object... args) {
        return MessageFormat.format(this.getMessageTemplate(), args);
    }
}
