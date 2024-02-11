package com.pigeon.usermanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Schema(description = "Модель пользователя")
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank
    @Schema(description = "Почта")
    private String email;

    @NotBlank
    @Schema(description = "Логин")
    private String login;

    @NotBlank
    @Schema(description = "Имя")
    private String name;

    @Schema(description = "Аватар")
    private String imageUrl;
}
