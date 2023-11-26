package com.pigeon.usermanager.exception;

import com.pigeon.usermanager.exception.enums.TokenErrorCode;

public class TokenServiceException extends ServiceRuntimeException {

    public TokenServiceException(TokenErrorCode errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }
}
