package com.pigeon.usermanager.contoller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "API для управления информацией о пользователях")
@RestController
@RequestMapping("/v1/user")
public class UserController {

    @GetMapping("/logout")
    @Operation(description = "Позволяет выйти пользователю из активной сессии")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.ok().build();
    }
}
