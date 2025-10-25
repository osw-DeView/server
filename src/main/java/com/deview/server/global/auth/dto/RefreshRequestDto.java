package com.deview.server.global.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshRequestDto {

    @NotBlank
    private String refreshToken;
}
