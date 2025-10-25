package com.deview.server.global.auth.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Getter
public class JwtUtil {
    private static SecretKey secretKey;
    private static Long accessTokenExpiresIn;
    private static Long refreshTokenExpiresIn;

    @Value("${spring.security.jwt.secret}")
    public void setSecretKeyStatic(String secretKeyString) {
        JwtUtil.secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes(StandardCharsets.UTF_8));
    }

    @Value("${spring.security.jwt.access.expiration}")
    public void setAccessTokenExpiresInStatic(Long expires) {
        JwtUtil.accessTokenExpiresIn = expires;
    }

    @Value("${spring.security.jwt.refresh.expiration}")
    public void setRefreshTokenExpiresInStatic(Long expires) {
        JwtUtil.refreshTokenExpiresIn = expires;
    }

    // JWT 클레임 username 파싱
    public static String getUsername(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("sub", String.class);
    }

    // JWT 클레임 role 파싱
    public static String getRole(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    // JWT 유효 여부 (위조, 시간, Access/Refresh 여부)
    public static Boolean isValid(String token, Boolean isAccess) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String type = claims.get("type", String.class);
            if (type == null) return false;

            if (isAccess && !type.equals("access")) return false;
            if (!isAccess && !type.equals("refresh")) return false;

            return true;

        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // JWT(Access/Refresh) 생성
    public static String createJWT(String username, String role, Boolean isAccess) {

        long now = System.currentTimeMillis();
        long expiry = isAccess ? accessTokenExpiresIn : refreshTokenExpiresIn;
        String type = isAccess ? "access" : "refresh";

        return Jwts.builder()
                .claim("sub", username)
                .claim("role", role)
                .claim("type", type)
                .issuedAt(new Date(now))
                .expiration(new Date(now + expiry))
                .signWith(secretKey)
                .compact();
    }

}
