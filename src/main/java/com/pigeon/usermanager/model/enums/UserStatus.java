package com.pigeon.usermanager.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static java.util.Arrays.stream;

@Getter
@RequiredArgsConstructor
public enum UserStatus {

    ACTIVE("Активен"),
    BLOCKED("Заблокирован"),
    DELETED("Удален"),
    ;

    private final String label;

    public Optional<UserStatus> of(String label) {
        return stream(values()).filter(r -> r.label.equals(label)).findFirst();
    }
}
