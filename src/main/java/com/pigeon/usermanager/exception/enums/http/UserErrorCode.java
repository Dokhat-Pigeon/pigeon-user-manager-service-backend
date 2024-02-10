package com.pigeon.usermanager.exception.enums.http;

import com.pigeon.usermanager.exception.enums.HttpErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements HttpErrorCode {

    DIFFERENT_CONFIRM_PASSWORD("Подтверждение пароля и пароль не совпадают", HttpStatus.BAD_REQUEST),
    WRONG_PASSWORD("Неверный пароль", HttpStatus.BAD_REQUEST),
    WRONG_EMAIL_ADDRESS("Неправильно указан адрес электронной почты", HttpStatus.BAD_REQUEST),
    WRONG_EMAIL_OR_LOGIN("Неправильно указан логин/адрес электронной почты", HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_USE("Электронная почта уже используется", HttpStatus.BAD_REQUEST),
    LOGIN_ALREADY_USE("Логин уже используется", HttpStatus.BAD_REQUEST),
    WRONG_VERIFICATION_URL("Неверный URL верификации почты", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("Пользователя не существует или он был удален", HttpStatus.NOT_FOUND),
    ;

    private final String messageTemplate;
    private final HttpStatus httpStatus;
}
