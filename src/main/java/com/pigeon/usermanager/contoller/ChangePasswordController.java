package com.pigeon.usermanager.contoller;

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

@Tag(name = "Change Password", description = "API для смены пароля неавторизованного пользователя")
@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
@RequestMapping("/v1/change-password")
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

    @GetMapping("/verify/{uid}")
    @Operation(description = "Проверка возможности смены пароля")
    public ResponseEntity<Void> verify(
            @PathVariable @Parameter(description = "Идентификатор пользователя") String uid
    ) {
        changePasswordService.verify(uid);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/confirm/{uid}")
    @Operation(description = "Подтверждение смены пароля")
    public ResponseEntity<Void> confirm(
            @PathVariable @Parameter(description = "Идентификатор пользователя") String uid
    ) {
        changePasswordService.confirm(uid);
        log.info("Password was changed for user with uid: {}", uid);
        return ResponseEntity.ok().build();
    }
}
