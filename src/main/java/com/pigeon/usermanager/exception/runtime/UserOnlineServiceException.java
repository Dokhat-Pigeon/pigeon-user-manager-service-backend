package com.pigeon.usermanager.exception.runtime;

import com.pigeon.usermanager.exception.ServiceRuntimeException;
import com.pigeon.usermanager.exception.enums.runtime.UserOnlineErrorCode;

public final class UserOnlineServiceException extends ServiceRuntimeException {

    public UserOnlineServiceException(UserOnlineErrorCode errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }

    public UserOnlineServiceException(UserOnlineErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }
}
