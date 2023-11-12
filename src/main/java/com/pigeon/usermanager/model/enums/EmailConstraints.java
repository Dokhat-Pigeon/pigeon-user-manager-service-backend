package com.pigeon.usermanager.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmailConstraints {

    USERNAME("username"),
    VERIFICATION_URL("verificationURL"),
    ;

    private final String label;
}
