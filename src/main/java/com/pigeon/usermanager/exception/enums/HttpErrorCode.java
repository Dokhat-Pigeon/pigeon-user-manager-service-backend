package com.pigeon.usermanager.exception.enums;

import org.springframework.http.HttpStatus;

public interface HttpErrorCode extends ErrorCode {

    HttpStatus getHttpStatus();
}
