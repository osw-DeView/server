package com.deview.server.global.auth.handler;

import com.deview.server.global.response.Status;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Qualifier("LoginFailureHandler")
@Component
@RequiredArgsConstructor
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("로그인에 실패했습니다: {}", exception.getMessage());

        Status errorStatus;
        if (exception instanceof BadCredentialsException) {
            errorStatus = Status.LOGIN_FAILED_INVALID_PASSWORD;
        } else if (exception instanceof InternalAuthenticationServiceException || exception instanceof UsernameNotFoundException) {
            errorStatus = Status.MEMBER_NOT_FOUND;
        } else {
            errorStatus = Status.UNAUTHORIZED;
        }

        throw new JwtException(errorStatus.getMessage());
    }
}
