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

@Tag(name = "Black List", description = "API для работы с черным списком пользователей")
@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
@RequestMapping("/v1/blacklist")
public class BlackListController {

    private final BlackListService blackListService;

    @PostMapping("/{uid}")
    @Operation(description = "Добавить пользователя в черный список")
    public ResponseEntity<Void> add(
            @PathVariable @Parameter(description = "Идентификатор пользователя") String uid
    ) {
        blackListService.add(uid);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uid}")
    @Operation(description = "Удалить пользователя из черного списка")
    public ResponseEntity<Void> delete(
            @PathVariable @Parameter(description = "Идентификатор пользователя") String uid
    ) {
        blackListService.delete(uid);
        return ResponseEntity.ok().build();
    }
}
