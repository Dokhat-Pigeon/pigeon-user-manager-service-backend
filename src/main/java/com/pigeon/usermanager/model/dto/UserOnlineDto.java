package com.pigeon.usermanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Schema(description = "Модель для передачи информации об онлайн статусе пользователя")
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOnlineDto {

    @Schema(description = "Флаг нахождения в сети")
    private Boolean isOnline;

    @Schema(description = "Последнее время нахождения в сети")
    private ZonedDateTime lastOnlineDate;
}
