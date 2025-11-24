package com.deview.server.global.auth.service;


import com.deview.server.global.auth.dto.JwtResponseDto;
import com.deview.server.global.auth.dto.RefreshRequestDto;
import com.deview.server.global.auth.util.JwtUtil;
import com.deview.server.global.redis.RefreshTokenService;
import com.deview.server.global.response.GeneralException;
import com.deview.server.global.response.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final RefreshTokenService refreshTokenService;

    // Refresh 토큰으로 Access 토큰 재발급 로직 (Rotate 포함)
    @Transactional
    public JwtResponseDto refreshRotate(RefreshRequestDto dto) {

        String refreshToken = dto.getRefreshToken();

        // Refresh 토큰 검증
        Boolean isValid = JwtUtil.isValid(refreshToken, false);
        if (!isValid) {
            throw new GeneralException(Status.INVALID_REFRESH_TOKEN);
        }

        // RefreshEntity 존재 확인 (화이트리스트)
        if (!existsRefresh(refreshToken)) {
            throw new GeneralException(Status.INVALID_REFRESH_TOKEN);
        }

        // 정보 추출
        String username = JwtUtil.getUsername(refreshToken);
        String role = JwtUtil.getRole(refreshToken);

        // 토큰 생성
        String newAccessToken = JwtUtil.createJWT(username, role, true);
        String newRefreshToken = JwtUtil.createJWT(username, role, false);

        removeRefresh(refreshToken);
        addRefresh(newRefreshToken,username);

        return new JwtResponseDto(newAccessToken, newRefreshToken);
    }

    // refresh token 발급 후 저장
    @Transactional
    public void addRefresh(String refreshToken, String username) {
        refreshTokenService.saveRefreshToken(refreshToken,username);
    }

    // refresh 존재 확인
    @Transactional(readOnly = true)
    public Boolean existsRefresh(String refreshToken) {
        return refreshTokenService.existsRefresh(refreshToken);
    }

    // refresh token 삭제
    public void removeRefresh(String refreshToken) {
        refreshTokenService.removeRefreshToken(refreshToken);
    }

    @Transactional(readOnly = true)
    public Boolean existsUsername(String username) {
        return refreshTokenService.existsUsername(username);
    }
}
