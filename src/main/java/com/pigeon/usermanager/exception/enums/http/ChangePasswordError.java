package com.pigeon.usermanager.exception.enums.http;

import com.pigeon.usermanager.exception.enums.HttpErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ChangePasswordError implements HttpErrorCode {

    WRONG_EMAIL_ADDRESS("Пользователь с почтой {0} отсутствует в системе", HttpStatus.NOT_FOUND),
    CONFIRMATION_ERROR("Неверная ссылка подтверждения, попробуйте ещё раз", HttpStatus.NOT_FOUND),
    CONFIRM_PASSWORD_ERROR("Пароль и подтверждение пароля не совпадают", HttpStatus.BAD_REQUEST),
    CHANGE_PASSWORD_ERROR("Непредвиденная ошибка сервиса, попробуйте позже", HttpStatus.INTERNAL_SERVER_ERROR),
    ;

    private final String messageTemplate;
    private final HttpStatus httpStatus;
}
