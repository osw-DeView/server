package com.deview.server.global.config;

import com.deview.server.global.auth.filter.JwtExceptionFilter;
import com.deview.server.global.auth.filter.JwtFilter;
import com.deview.server.global.auth.filter.LoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final AuthenticationSuccessHandler loginSuccessHandler;
    private final AuthenticationFailureHandler loginFailureHandler;

    private static final String[] ALLOWED = {
            "/",
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "test/**",
            "/auth/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // URL 권한 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(ALLOWED).permitAll()
                        .anyRequest().authenticated()
                )

                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .headers(httpSecurityHeadersConfigurer ->
                        httpSecurityHeadersConfigurer.frameOptions(
                                HeadersConfigurer.FrameOptionsConfig::disable)
                )

                .addFilterBefore(new JwtFilter(), LogoutFilter.class)
                .addFilterBefore(new JwtExceptionFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class)

        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 커스텀 자체 로그인 필터를 위한 AuthenticationManager Bean 수동 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public LoginFilter loginFilter() throws Exception {
        return new LoginFilter(authenticationManager(authenticationConfiguration),
                loginSuccessHandler, loginFailureHandler);
    }
}
