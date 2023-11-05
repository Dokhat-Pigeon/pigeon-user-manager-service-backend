package com.pigeon.usermanager.contoller;

import com.pigeon.usermanager.model.dto.AuthorizationDto;
import com.pigeon.usermanager.model.dto.RegistrationDto;
import com.pigeon.usermanager.model.dto.TokenDto;
import com.pigeon.usermanager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "API для управления состоянием пользователей")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    @Operation(description = "Регистрация пользователя")
    public ResponseEntity<Void> register(@RequestBody RegistrationDto registrationDto) {
        userService.register(registrationDto);
        log.info("User with login {} was registered.", registrationDto.getLogin());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/verification/{uid}")
    @Operation(description = "Проверка подтверждения почты")
    public ResponseEntity<TokenDto> verification(
            @PathVariable @Parameter(description = "Идентификатор пользователя") String uid
    ) {
        TokenDto tokenDto = userService.verify(uid);
        log.info("User with uid {} was verified.", uid);
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/authorization")
    @Operation(description = "Авторизация пользователя")
    public ResponseEntity<TokenDto> login(@RequestBody AuthorizationDto authorizationDto) {
        TokenDto tokenDto = userService.login(authorizationDto);
        log.info("User {} was authorized.", authorizationDto.getLoginOrEmail());
        return ResponseEntity.ok(tokenDto);
    }

    @GetMapping("/logout")
    @Operation(description = "Позволяет выйти пользователю из активной сессии")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.ok().build();
    }
}
