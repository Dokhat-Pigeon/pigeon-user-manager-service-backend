package com.pigeon.usermanager.contoller;

import com.pigeon.usermanager.model.dto.UserDto;
import com.pigeon.usermanager.model.entity.UserEntity;
import com.pigeon.usermanager.service.BlackListService;
import com.pigeon.usermanager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@Tag(name = "Black List", description = "API для работы с черным списком пользователей")
@RestController
@RequestMapping("/v1/blacklist")
@Validated
@RequiredArgsConstructor
@Slf4j
public class BlackListController {

    private final BlackListService blackListService;
    private final UserService userService;

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

    @GetMapping
    @Operation(description = "Получение постранично (пагинация) пользователей из черного списка текущего пользователя в сессии")
    public ResponseEntity<Page<UserDto>> get(
            @ParameterObject @Parameter(description = "Страница") Pageable pageable
    ) {
        UserEntity owner = userService.getCurrentUser();
        Page<UserDto> users = blackListService.getUsersFromBlacklist(owner, pageable);
        log.info(
                "Returned blacklist item count: {}, for user: {}, request: {}",
                users.stream().count(),
                owner.getLogin(),
                pageable
        );
        return ResponseEntity.ok(users);
    }
}
