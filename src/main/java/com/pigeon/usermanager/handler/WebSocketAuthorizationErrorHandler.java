package com.pigeon.usermanager.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;

import java.nio.file.AccessDeniedException;

@Component
@Slf4j
public class WebSocketAuthorizationErrorHandler extends StompSubProtocolErrorHandler {

    @Override
    @NonNull
    protected Message<byte[]> handleInternal(@NonNull StompHeaderAccessor errorHeaderAccessor,
                                             @NonNull byte[] errorPayload,
                                             Throwable cause,
                                             StompHeaderAccessor clientHeaderAccessor) {
        if (cause != null) {
            log.error("Stomp error: {}", cause.getMessage());
            String message = cause.getCause() instanceof AccessDeniedException
                    ? cause.getMessage()
                    : "Server error";
            errorHeaderAccessor.setMessage(message);
        }
        return super.handleInternal(errorHeaderAccessor, errorPayload, cause, clientHeaderAccessor);
    }
}
