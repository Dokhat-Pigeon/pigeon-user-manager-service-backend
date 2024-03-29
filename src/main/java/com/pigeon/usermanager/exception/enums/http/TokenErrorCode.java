package com.pigeon.usermanager.exception.enums.http;

import com.pigeon.usermanager.exception.enums.HttpErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TokenErrorCode implements HttpErrorCode {

    INVALID_REFRESH_TOKEN("Невалидный Refresh токен", HttpStatus.FORBIDDEN),
    TOKEN_NOT_FOUND("Отсутствует токен авторизации", HttpStatus.NOT_FOUND),
    USER_STATE_CHANGED("Состояние пользователя изменилось", HttpStatus.FORBIDDEN),
    USER_NOT_AVAILABLE("Пользователь недоступен или был удален", HttpStatus.FORBIDDEN),
    ;

    private final String messageTemplate;
    private final HttpStatus httpStatus;
}
