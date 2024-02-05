package com.pigeon.usermanager.contoller;

import com.pigeon.usermanager.model.entity.UserEntity;
import com.pigeon.usermanager.service.BlackListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@Tag(name = "Black List", description = "API для работы с черным списком пользователей")
@RestController
@RequestMapping("/v1/blacklist")
@Validated
@RequiredArgsConstructor
@Slf4j
public class BlackListController {

    private final BlackListService blackListService;

    @PostMapping("/{id}")
    @Operation(description = "Добавить пользователя в черный список")
    public ResponseEntity<Void> add(
            @PathVariable @Positive @Parameter(description = "Идентификатор пользователя") Long id
    ) {
        blackListService.addUser(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удалить пользователя из черного списка")
    public ResponseEntity<Void> delete(
            @PathVariable @Positive @Parameter(description = "Идентификатор пользователя") Long id
    ) {
        blackListService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{page}")
    @Operation(description = "Получение постранично (пагинация) пользователей из черного списка текущего пользователя в сессии")
    public ResponseEntity<List<UserEntity>> get(
            @PathVariable @Positive @Parameter(description = "Страница") Integer page
    ) {
        List<UserEntity> users = blackListService.getUsersFromBlacklist(page);
        return ResponseEntity.ok(users);
    }
}
