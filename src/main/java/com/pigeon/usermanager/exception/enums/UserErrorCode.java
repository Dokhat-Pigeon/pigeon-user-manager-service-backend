package com.pigeon.usermanager.exception.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

    DIFFERENT_CONFIRM_PASSWORD("Подтверждение пароля и пароль не совпадают"),
    WRONG_PASSWORD("Неверный пароль"),
    WRONG_EMAIL_ADDRESS("Неправильно указан адрес электронной почты"),
    WRONG_EMAIL_OR_LOGIN("Неправильно указан логин/адрес электронной почты"),
    EMAIL_ALREADY_USE("Электронная почта уже используется"),
    LOGIN_ALREADY_USE("Логин уже используется"),
    WRONG_VERIFICATION_URL("Неверный URL верификации почты", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("Пользователь не существует или был удален"),
    ;

    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    private final String messageTemplate;

    UserErrorCode(String messageTemplate, HttpStatus httpStatus) {
        this.messageTemplate = messageTemplate;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage(Object... args) {
        return MessageFormat.format(messageTemplate, args);
    }
}
