package com.deview.server.domain.interview.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InterviewCategoryItemDto {
    @NotBlank
    @Schema(
            description = "카테고리",
            allowableValues = {"Database", "Network", "OperatingSystem",
                    "Spring"},
            example = "Database" // 대표 예시
    )
    private String category;           //카테고리

    @Schema(
            description = "키워드 목록",
            example = "[\"sequence\", \"DBMS\", \"view\", \"정규화\", \"이상현상\", \"DB설계\", \"무결성\", \"MySQL\"]"
    )
    private List<String> keyword;    //키워드 목록
}
