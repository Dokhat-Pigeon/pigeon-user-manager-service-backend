package com.pigeon.usermanager.exception;

import com.pigeon.usermanager.exception.enums.ChangePasswordError;

public class ChangePasswordException extends ServiceRuntimeException {

    public ChangePasswordException(ChangePasswordError errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }
}
