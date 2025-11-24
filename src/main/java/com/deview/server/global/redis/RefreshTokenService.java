package com.deview.server.global.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void saveRefreshToken(String refreshToken, String username) {
        RefreshToken token = new RefreshToken(refreshToken, username);
        refreshTokenRepository.save(token);
    }

    @Transactional
    public void removeRefreshToken(String refreshToken) {
        refreshTokenRepository.findRefreshTokenByJwtRefreshToken(refreshToken)
                .ifPresent(refreshTokenRepository::delete);
    }

    @Transactional(readOnly = true)
    public Boolean existsRefresh(String refreshToken) {
        return refreshTokenRepository.existsByJwtRefreshToken(refreshToken);
    }

    @Transactional(readOnly = true)
    public Boolean existsUsername(String username) {
        return refreshTokenRepository.existsByUsername(username);
    }

}
