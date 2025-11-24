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
    @Schema(description = "하위카테고리", example = "운영체제")
    private String secondCategory;
}