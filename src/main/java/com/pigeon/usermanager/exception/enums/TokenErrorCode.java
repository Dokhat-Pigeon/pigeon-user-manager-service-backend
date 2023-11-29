package com.pigeon.usermanager.exception.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

@Getter
@RequiredArgsConstructor
public enum TokenErrorCode implements ErrorCode {

    INVALID_REFRESH_TOKEN("Невалидный Refresh токен"),
    TOKEN_NOT_FOUND("Отсутствует токен авторизации"),
    ;

    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    private final String messageTemplate;

    TokenErrorCode(String messageTemplate, HttpStatus httpStatus) {
        this.messageTemplate = messageTemplate;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage(Object... args) {
        return MessageFormat.format(messageTemplate, args);
    }
}

