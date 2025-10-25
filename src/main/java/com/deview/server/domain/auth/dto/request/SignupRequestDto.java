package com.deview.server.domain.auth.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupRequestDto {

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(description = "아이디", example = "test1234")
    String username;

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(description = "비밀번호", example = "test1234!")
    String password;

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(description = "닉네임", example = "user")
    String nickname;

}
