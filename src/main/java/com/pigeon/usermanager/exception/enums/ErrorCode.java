package com.pigeon.usermanager.exception.enums;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    String getMessage(Object... args);

    HttpStatus getHttpStatus();
}
