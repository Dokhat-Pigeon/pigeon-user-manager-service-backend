package com.pigeon.usermanager.exception.enums.ws;

import com.pigeon.usermanager.exception.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WebSocketErrorCode implements ErrorCode {

    ACCESS_DENIED("К данному ресурсу запрещён доступ неавторизованным пользователям"),
    DESTINATION_NOT_FOUND("Неизвестное направление подключения"),
    FORBIDDEN_DESTINATION("Нет прав для подключения к этому направлению")
    ;

    private final String messageTemplate;
}
