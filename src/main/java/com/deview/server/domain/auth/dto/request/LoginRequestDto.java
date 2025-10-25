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
public class LoginRequestDto {
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
}
