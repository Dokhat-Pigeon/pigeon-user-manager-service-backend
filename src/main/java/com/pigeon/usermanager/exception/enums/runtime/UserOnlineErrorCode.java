package com.pigeon.usermanager.exception.enums.runtime;

import com.pigeon.usermanager.exception.enums.ErrorCode;
import com.pigeon.usermanager.security.JwtAuthentication;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserOnlineErrorCode implements ErrorCode {

    USER_ONLINE_NOT_FOUND("Онлайн статус пользователя {0} отсутствует в системе"),
    AUTH_PARSING_ERROR("Ошибка парсинга " + JwtAuthentication.class.getSimpleName())
    ;

    private final String messageTemplate;
}
