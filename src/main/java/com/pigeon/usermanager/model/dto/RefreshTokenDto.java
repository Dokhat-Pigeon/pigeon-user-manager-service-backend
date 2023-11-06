package com.pigeon.usermanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Модель для передачи refresh токена")
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenDto {

    @Schema(description = "Refresh token")
    private String refreshToken;
}
