package com.deview.server.global.auth.filter;


import com.deview.server.global.auth.util.JwtUtil;
import com.deview.server.global.response.Status;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getRequestURI().equals("/auth/jwt/refresh")) {
            filterChain.doFilter(request, response);
            return;
        }

        //Authorization 헤더 검증
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!authorization.startsWith("Bearer ")) {
            throw new JwtException(Status.JWT_INVALID.getMessage());
        }

        //Bearer 부분 제거
        String accessToken = authorization.split(" ")[1];

        //토큰 소멸 시간 검증
        if (JwtUtil.isValid(accessToken,true)) {

            String username = JwtUtil.getUsername(accessToken);

            String role = JwtUtil.getRole(accessToken);
            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));

            Authentication auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(auth);

            filterChain.doFilter(request, response);
        } else {
            throw new JwtException(Status.JWT_EXPIRED_TOKEN.getMessage());
        }

    }
}
