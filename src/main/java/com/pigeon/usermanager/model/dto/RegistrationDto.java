package com.pigeon.usermanager.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Schema(description = "Модель для формы регистрации")
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {

    @NotBlank
    @Schema(description = "Почта")
    private String email;

    @NotBlank
    @Schema(description = "Логин")
    private String login;

    @NotBlank
    @Schema(description = "Имя")
    private String name;

    @NotBlank
    @Schema(description = "Пароль")
    private String password;

    @NotBlank
    @Schema(description = "Подтверждение пароля")
    @JsonProperty("confirm_password")
    private String confirmPassword;
}
