package com.pigeon.usermanager.security;

import com.pigeon.usermanager.model.enums.UserRole;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setFirstName(claims.get(ClaimsConstants.NAME_KEY, String.class));
        jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }

    public static JwtAuthentication generateAnonymous() {
        JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(Set.of(UserRole.ANONYMOUS));
        jwtInfoToken.setFirstName("anonymous");
        jwtInfoToken.setUsername("anonymous");
        return jwtInfoToken;
    }

    public static boolean isAnonymous(Authentication authentication) {
        return authentication.getAuthorities().contains(UserRole.ANONYMOUS);
    }

    private static Set<UserRole> getRoles(Claims claims) {
        String role = claims.get(ClaimsConstants.ROLE_KEY, String.class);
        return Set.of(UserRole.valueOf(role));
    }

}