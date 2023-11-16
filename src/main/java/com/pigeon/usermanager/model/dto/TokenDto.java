package com.pigeon.usermanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Schema(description = "Модель для передачи токена авторизации и рефреш токена")
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto implements Serializable {

    @Schema(description = "Токен авторизации")
    private String authorization;

    @Schema(description = "Рефреш токен")
    private String refresh;
}
