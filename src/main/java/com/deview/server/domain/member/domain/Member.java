package com.deview.server.domain.member.domain;


import com.deview.server.domain.auth.dto.request.SignupRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @NotNull
    @Column(updatable = false)
    private String username;

    @NotNull
    private String password;

    @Column(unique = true)
    @NotNull
    private String nickname;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member toEntity(SignupRequestDto requestDto, PasswordEncoder passwordEncoder) {
        return Member.builder()
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .role(Role.USER)
                .nickname(requestDto.getNickname())
                .build();
    }

}
