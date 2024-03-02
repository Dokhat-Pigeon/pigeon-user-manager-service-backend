package com.pigeon.usermanager.contoller;

import com.pigeon.usermanager.model.dto.AuthorizationDto;
import com.pigeon.usermanager.model.dto.RegistrationDto;
import com.pigeon.usermanager.model.dto.TokenDto;
import com.pigeon.usermanager.model.dto.UserDto;
import com.pigeon.usermanager.model.entity.UserEntity;
import com.pigeon.usermanager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Tag(name = "User", description = "API для управления состоянием пользователей")
@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    @Operation(description = "Регистрация пользователя")
    public ResponseEntity<Void> register(@RequestBody @Valid RegistrationDto registration) {
        userService.register(registration);
        log.info("User with login {} was registered.", registration.getLogin());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/verification/{uuid}")
    @Operation(description = "Проверка подтверждения почты")
    public ResponseEntity<TokenDto> verify(
            @PathVariable @Parameter(description = "Идентификатор записи о подтверждения почты") UUID uuid
    ) {
        TokenDto tokenDto = userService.verify(uuid);
        log.info("Record with uuid {} was verified.", uuid);
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/authorization")
    @Operation(description = "Авторизация пользователя")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid AuthorizationDto authorization) {
        TokenDto tokenDto = userService.login(authorization);
        log.info("User {} was authorized.", authorization.getLoginOrEmail());
        return ResponseEntity.ok(tokenDto);
    }

    @GetMapping("/logout")
    @Operation(description = "Позволяет выйти пользователю из активной сессии")
    public ResponseEntity<Void> logout() {
        UserEntity user = userService.logout();
        log.info("User {} was logout.", user.getLogin());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{login}")
    @Operation(description = "Получить информацию о пользователе")
    public ResponseEntity<UserDto> getUserByLogin(
            @PathVariable @Parameter(description = "Логин пользователя") @NotNull @Valid String login
    ) {
        log.info("Getting user by login: {}", login);
        UserDto user = userService.getDtoByLogin(login);
        log.info("Returned user: {}, by login: {}", user, login);
        return ResponseEntity.ok(user);
    }
}
