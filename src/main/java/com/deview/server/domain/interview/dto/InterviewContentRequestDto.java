package com.deview.server.domain.interview.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InterviewContentRequestDto {

    @NotBlank
    @Schema(description = "카테고리", example = "Database")
    private String category;

    @NotBlank
    @Schema(description = "키워드", example = "sequence")
    private String keyword;
}
