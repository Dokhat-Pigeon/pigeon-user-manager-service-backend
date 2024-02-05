package com.pigeon.usermanager.exception;

import com.pigeon.usermanager.exception.enums.HttpErrorCode;
import lombok.Getter;

@Getter
public abstract class ServiceHttpException extends ServiceRuntimeException {

    public ServiceHttpException(HttpErrorCode errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }
}
