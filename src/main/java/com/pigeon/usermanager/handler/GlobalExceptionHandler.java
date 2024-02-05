package com.pigeon.usermanager.handler;

import com.pigeon.usermanager.exception.ServiceHttpException;
import com.pigeon.usermanager.exception.enums.HttpErrorCode;
import com.pigeon.usermanager.model.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceHttpException.class)
    public ResponseEntity<ErrorDto> handleUserServiceException(ServiceHttpException ex) {
        log.error(ex.getMessage(), ex);
        ErrorDto error = ErrorDto.builder()
                .errorCode(ex.getErrorCode())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, ((HttpErrorCode)ex.getErrorCode()).getHttpStatus());
    }
}
