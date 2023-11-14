package com.pigeon.usermanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Schema(description = "Модель для смены пароля")
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDto {

    @Schema(description = "Идентификатор смены пароля")
    private UUID uuid;

    @Schema(description = "Новый пароль")
    private String newPassword;

    @Schema(description = "Подтверждение нового пароля")
    private String confirmNewPassword;
}
