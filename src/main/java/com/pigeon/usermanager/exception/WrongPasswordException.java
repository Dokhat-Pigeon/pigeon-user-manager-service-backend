package com.pigeon.usermanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongPassword extends RuntimeException {

    public WrongPassword(String message) {
        super(message);
    }
}
