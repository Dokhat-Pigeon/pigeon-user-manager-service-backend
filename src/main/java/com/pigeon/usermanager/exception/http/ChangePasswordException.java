package com.pigeon.usermanager.exception.http;

import com.pigeon.usermanager.exception.ServiceHttpException;
import com.pigeon.usermanager.exception.enums.http.ChangePasswordError;

public final class ChangePasswordException extends ServiceHttpException {

    public ChangePasswordException(ChangePasswordError errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }

    public ChangePasswordException(ChangePasswordError errorCode, Object... args) {
        super(errorCode, args);
    }
}
