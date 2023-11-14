package com.pigeon.usermanager.exception.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

@Getter
@RequiredArgsConstructor
public enum ChangePasswordError implements ErrorCode {

    WRONG_EMAIL_ADDRESS("Пользователь с почтой {0} отсутствует в системе"),
    CONFIRMATION_ERROR("Неверная ссылка подтверждения, попробуйте ещё раз"),
    CONFIRM_PASSWORD_ERROR("Пароль и подтверждение пароля не совпадают", HttpStatus.BAD_REQUEST),
    CHANGE_PASSWORD_ERROR("Непредвиденная ошибка сервиса, попробуйте позже", HttpStatus.INTERNAL_SERVER_ERROR),
    ;

    private HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    private final String messageTemplate;

    ChangePasswordError(String messageTemplate, HttpStatus httpStatus) {
        this.messageTemplate = messageTemplate;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage(Object... args) {
        return MessageFormat.format(messageTemplate, args);
    }
}
