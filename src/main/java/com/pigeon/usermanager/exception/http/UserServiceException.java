package com.pigeon.usermanager.exception.http;

import com.pigeon.usermanager.exception.ServiceHttpException;
import com.pigeon.usermanager.exception.enums.http.UserErrorCode;

public class UserServiceException extends ServiceHttpException {

    public UserServiceException(UserErrorCode errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }

    public UserServiceException(UserErrorCode errorCode, Object... args) {
        super(errorCode, new Throwable(), args);
    }
}
