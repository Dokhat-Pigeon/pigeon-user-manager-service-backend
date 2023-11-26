package com.pigeon.usermanager.exception;

import com.pigeon.usermanager.exception.enums.UserErrorCode;

public class UserServiceException extends ServiceRuntimeException {

    public UserServiceException(UserErrorCode errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }
}
