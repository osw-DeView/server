package com.deview.server.domain.auth.controller;


import com.deview.server.domain.auth.dto.request.SignupRequestDto;
import com.deview.server.domain.auth.service.AuthService;
import com.deview.server.global.response.ApiResponse;
import com.deview.server.global.response.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "auth", description = "인증 관련 api")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signUp")
    @Operation(summary = "회원가입")
    public ApiResponse<?> signUp(
            @RequestBody @Valid SignupRequestDto signupRequestDto) {

        authService.signUp(signupRequestDto);
        return ApiResponse.success(Status.OK.getCode(),
                Status.CREATED.getMessage(), null);
    }

}
