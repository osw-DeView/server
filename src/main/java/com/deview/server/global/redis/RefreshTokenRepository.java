package com.deview.server.global.redis;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
    Optional<RefreshToken> findRefreshTokenByJwtRefreshToken(String refreshToken);
    boolean existsByJwtRefreshToken(String jwtRefreshToken);

    boolean existsByUsername(String username);
}
