package com.pigeon.usermanager.handler;

import com.pigeon.usermanager.exception.UserServiceException;
import com.pigeon.usermanager.model.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<ErrorDto> handleUSerServiceException(UserServiceException ex) {
        log.error(ex.getMessage(), ex);
        ErrorDto error = ErrorDto.builder()
                .errorCode(ex.getErrorCode())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, ex.getErrorCode().getHttpStatus());
    }
}
