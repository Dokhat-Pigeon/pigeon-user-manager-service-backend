package com.pigeon.usermanager.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public enum WsTopic {

    USER_ONLINE("/user/online/%s", Pattern.compile("/user/online/.+"), false)
    ;

    private final String path;
    private final Pattern pattern;
    @Getter
    private final boolean isSecure;

    public String getPath(Object... args) {
        return path.formatted(args);
    }

    public static Optional<WsTopic> byPattern(String path) {
        return Arrays.stream(values()).filter(topic -> topic.pattern.matcher(path).matches()).findFirst();
    }
}
