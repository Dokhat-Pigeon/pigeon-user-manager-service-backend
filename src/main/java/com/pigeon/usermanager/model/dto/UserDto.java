package com.pigeon.usermanager.model.dto;

import com.pigeon.usermanager.model.enums.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Schema(description = "Модель для передачи информации о пользователе")
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @Schema(description = "Логин")
    private String login;

    @Schema(description = "Имя")
    private String name;

    @Schema(description = "Аватарка")
    private File image;

    @Schema(description = "Статус в системе")
    private UserStatus status;

    @Schema(description = "Информация об онлайн статусе")
    private UserOnlineDto online;
}
