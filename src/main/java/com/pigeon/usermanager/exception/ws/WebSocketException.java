package com.pigeon.usermanager.exception.ws;

import com.pigeon.usermanager.exception.ServiceRuntimeException;
import com.pigeon.usermanager.exception.enums.ws.WebSocketErrorCode;

public final class WebSocketException extends ServiceRuntimeException {

    public WebSocketException(WebSocketErrorCode errorCode, Throwable cause, Object... args) {
        super(errorCode, cause, args);
    }

    public WebSocketException(WebSocketErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }

    public String getErrorCodeMessage() {
        return errorCode.getMessage();
    }
}
