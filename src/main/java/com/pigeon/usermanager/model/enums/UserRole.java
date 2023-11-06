package com.pigeon.usermanager.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static java.util.Arrays.stream;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    ADMINISTRATOR("Администратор"),
    TESTER("Тестер"),
    MODERATOR("Модератор"),
    USER("Пользователь"),
    ;

    private final String label;

    public Optional<UserRole> of(String label) {
        return stream(values()).filter(r -> r.label.equals(label)).findFirst();
    }
}
