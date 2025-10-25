package com.deview.server.global.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginResponseDto {
    private final String accessToken;
    private final String refreshToken;
}
