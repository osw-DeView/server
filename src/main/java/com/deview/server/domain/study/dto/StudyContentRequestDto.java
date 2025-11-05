package com.deview.server.domain.study.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudyContentRequestDto {

    @NotBlank
    @Schema(description = "상위카테고리", example = "Operating System")
    private String firstCategory;

    @NotBlank
    @Schema(description = "하위카테고리", example = "프로세스의 주소 공간")
    private String secondCategory;

    @NotBlank
    @Schema(description = "제목", example = "프로세스의 주소 공간이란?")
    private String title;
}
