package com.pigeon.usermanager.contoller;

import com.pigeon.usermanager.model.dto.ChangePasswordDto;
import com.pigeon.usermanager.service.ChangePasswordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import java.util.UUID;

@Tag(name = "Change Password", description = "API для смены пароля неавторизованного пользователя")
@RestController
@RequestMapping("/v1/change-password")
@Validated
@RequiredArgsConstructor
@Slf4j
public class ChangePasswordController {

    private final ChangePasswordService changePasswordService;

    @PutMapping("/send")
    @Operation(description = "Отправка сообщения о смене пароля")
    public ResponseEntity<Void> sendEmail(
            @RequestParam @Email @Parameter(description = "Почта пользователя") String email
    ) {
        changePasswordService.sendEmail(email);
        log.info("Letter with password verification was sent to user with email: {}", email);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/verify/{uuid}")
    @Operation(description = "Проверка возможности смены пароля")
    public ResponseEntity<Void> verify(
            @PathVariable @Parameter(description = "Идентификатор смены пароля") UUID uuid
    ) {
        changePasswordService.verify(uuid);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/confirm")
    @Operation(description = "Подтверждение смены пароля")
    public ResponseEntity<Void> confirm(
            @RequestBody ChangePasswordDto changePassword
    ) {
        changePasswordService.confirm(changePassword);
        log.info("Password was changed for record with uuid: {}", changePassword.getUuid());
        return ResponseEntity.ok().build();
    }
}
