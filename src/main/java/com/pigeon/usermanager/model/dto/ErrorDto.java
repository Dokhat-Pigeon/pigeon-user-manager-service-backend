package com.pigeon.usermanager.model.dto;

import com.pigeon.usermanager.exception.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {

    private ErrorCode errorCode;

    private String message;
}
