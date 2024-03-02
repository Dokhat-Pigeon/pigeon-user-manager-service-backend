package com.pigeon.usermanager.exception.http;

import com.pigeon.usermanager.exception.ServiceHttpException;
import com.pigeon.usermanager.exception.enums.http.TokenErrorCode;

public final class TokenServiceException extends ServiceHttpException {

    public TokenServiceException(TokenErrorCode errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }

    public TokenServiceException(TokenErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }
}
