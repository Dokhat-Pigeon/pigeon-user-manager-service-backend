package com.pigeon.usermanager.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Optional;

import static java.util.Arrays.stream;

@Getter
@RequiredArgsConstructor
public enum UserRole implements GrantedAuthority {

    ADMINISTRATOR("Администратор"),
    TESTER("Тестер"),
    MODERATOR("Модератор"),
    USER("Пользователь"),
    ANONYMOUS("Гость")
    ;

    private final String label;

    public Optional<UserRole> of(String label) {
        return stream(values()).filter(r -> r.label.equals(label)).findFirst();
    }

    @Override
    public String getAuthority() {
        return label;
    }
}
