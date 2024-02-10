package com.pigeon.usermanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Schema(description = "Модель для формы авторизации")
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationDto {

    @NotBlank
    @Schema(description = "Логин или Почта")
    private String loginOrEmail;

    @NotBlank
    @Schema(description = "Пароль")
    private String password;
}
