package com.deview.server.global.auth.filter;

import com.deview.server.global.auth.util.ResponseUtil;
import com.deview.server.global.response.ApiResponse;
import com.deview.server.global.response.Body;
import com.deview.server.global.response.Status;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            chain.doFilter(request, response);
        } catch (JwtException ex) {
            String message = ex.getMessage();

            Status errorStatus;

            // JWT 관련 오류 처리
            if (Status.JWT_INVALID.getMessage().equals(message)) {
                errorStatus = Status.JWT_INVALID;
            }
            else if (Status.JWT_EXPIRED_TOKEN.getMessage().equals(message)) {
                errorStatus = Status.JWT_EXPIRED_TOKEN;
            }
            else if (Status.JWT_WRONG_TYPE_TOKEN.getMessage().equals(message)) {
                errorStatus = Status.JWT_WRONG_TYPE_TOKEN;
            }
            // 로그인/회원 관련 오류 처리 (LoginSuccessHandler, LoginFailureHandler에서 던져짐)
            else if (Status.EXISTS_REFRESH_TOKEN.getMessage().equals(message)) {
                errorStatus = Status.EXISTS_REFRESH_TOKEN;
            }
            else if (Status.LOGIN_FAILED_INVALID_PASSWORD.getMessage().equals(message)) {
                errorStatus = Status.LOGIN_FAILED_INVALID_PASSWORD;
            }
            else if (Status.MEMBER_NOT_FOUND.getMessage().equals(message)) {
                errorStatus = Status.MEMBER_NOT_FOUND;
            }
            // 그 외 정의되지 않은 예외 처리
            else {
                errorStatus = Status.UNAUTHORIZED;
            }

            setResponse(response, errorStatus);
        }
    }

    private void setResponse(HttpServletResponse response, Status status) throws IOException {
        response.setStatus(status.getHttpStatus().value());

        Body body = status.getBody();
        ApiResponse<Object> apiResponse = ApiResponse.onFailure(
                body.getCode(),
                body.getMessage(),
                null
        );

        ResponseUtil.writeJson(response, apiResponse);
    }

}