package com.pigeon.usermanager.exception;

import com.pigeon.usermanager.exception.enums.ErrorCode;
import lombok.Getter;

@Getter
public abstract class ServiceRuntimeException extends RuntimeException {

    private final ErrorCode errorCode;

    public ServiceRuntimeException(ErrorCode errorCode, Throwable cause, Object... args) {
        super(errorCode.getMessage(args), cause);
        this.errorCode = errorCode;
    }

    public ServiceRuntimeException(ErrorCode errorCode, Object... args) {
        super(errorCode.getMessage(args));
        this.errorCode = errorCode;
    }
}
