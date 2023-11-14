package com.pigeon.usermanager.component;

import com.pigeon.usermanager.model.entity.UserEntity;
import io.jsonwebtoken.Claims;
import lombok.NonNull;

import java.security.Key;

public interface TokenProvider {

    public String generateAccessToken(@NonNull UserEntity user);

    public String generateRefreshToken(@NonNull UserEntity user);

    public boolean validateAccessToken(@NonNull String accessToken);

    public boolean validateRefreshToken(@NonNull String refreshToken);

    public Claims getAccessClaims(@NonNull String token);

    public Claims getRefreshClaims(@NonNull String token);
}
