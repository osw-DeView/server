package com.deview.server.global.auth.handler;

import com.deview.server.global.auth.dto.LoginResponseDto;
import com.deview.server.global.auth.service.JwtService;
import com.deview.server.global.auth.util.JwtUtil;
import com.deview.server.global.auth.util.ResponseUtil;
import com.deview.server.global.response.ApiResponse;
import com.deview.server.global.response.Status;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Qualifier("LoginSuccessHandler")
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException{

        // username, role
        String username =  authentication.getName();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        Boolean isUserExists = jwtService.existsUsername(username);
        if(isUserExists){
            throw new JwtException( Status.EXISTS_REFRESH_TOKEN.getMessage());
        }

        // JWT(Access/Refresh) 발급
        String accessToken = JwtUtil.createJWT(username, role, true);
        String refreshToken = JwtUtil.createJWT(username, role, false);

        // 발급한 Refresh DB 테이블 저장 (Refresh whitelist)
        jwtService.addRefresh(refreshToken, username);

        LoginResponseDto token = new LoginResponseDto(accessToken, refreshToken);

        ApiResponse<LoginResponseDto> apiResponse =
                ApiResponse.success(Status.OK.getCode(),
                Status.CREATED.getMessage(),token);

        // 응답
        ResponseUtil.writeJson(response,apiResponse);
    }

}