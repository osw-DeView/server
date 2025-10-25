package com.deview.server.global.auth.controller;


import com.deview.server.domain.auth.dto.request.LoginRequestDto;
import com.deview.server.domain.auth.service.AuthService;
import com.deview.server.global.auth.dto.JwtResponseDto;
import com.deview.server.global.auth.dto.LoginResponseDto;
import com.deview.server.global.auth.dto.RefreshRequestDto;
import com.deview.server.global.auth.service.JwtService;
import com.deview.server.global.auth.util.JwtUtil;
import com.deview.server.global.response.ApiResponse;
import com.deview.server.global.response.GeneralException;
import com.deview.server.global.response.Status;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class JwtController {
    private final AuthService authService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    // Refresh 토큰으로 Access 토큰 재발급 (Rotate 포함)
    @PostMapping("/auth/jwt/refresh")
    public ApiResponse<JwtResponseDto> jwtRefreshApi(
            @Validated @RequestBody RefreshRequestDto dto
    ) {

        JwtResponseDto jwtResponseDto = jwtService.refreshRotate(dto);
        return ApiResponse.success(Status.OK.getCode(),
                Status.OK.getMessage(),jwtResponseDto);
    }

    @PostMapping("/test/login")
    @Operation(summary = "로그인 테스트")
    public ApiResponse<LoginResponseDto> loginTest(@RequestBody @Valid LoginRequestDto loginRequestDto){

        String username = loginRequestDto.getUsername();
        String rawPassword = loginRequestDto.getPassword();

        UserDetails userDetails = authService.loadUserByUsername(username);

        if (!passwordEncoder.matches(rawPassword, userDetails.getPassword())) {
            throw new GeneralException(Status.LOGIN_FAILED_INVALID_PASSWORD);
        }

        if(jwtService.existsUsername(username)){
            throw new GeneralException(Status.EXISTS_REFRESH_TOKEN);
        }

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        String role = authorities.iterator().next().getAuthority();

        String accessToken = JwtUtil.createJWT(username, role, true);
        String refreshToken = JwtUtil.createJWT(username, role, false);

        jwtService.addRefresh(refreshToken, username);

        LoginResponseDto token = new LoginResponseDto(accessToken, refreshToken);

        return ApiResponse.success(Status.OK.getCode(),
                Status.CREATED.getMessage(),token);
    }
}
