package com.pigeon.usermanager.contoller;

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

@RestController
@RequestMapping("/v1/blacklist")
@RequiredArgsConstructor
@Validated
@Slf4j
@Tag(name = "Black List", description = "API для работы с черным списком пользователей")
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
}
