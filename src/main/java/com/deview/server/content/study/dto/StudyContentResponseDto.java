package com.deview.server.content.study.dto;

import com.deview.server.content.study.domain.StudyContent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyContentResponseDto {

    private String firstCategory;
    private String secondCategory;
    private String title;
    private String body;

    public static StudyContentResponseDto fromEntity(StudyContent entity) {
        return StudyContentResponseDto.builder()
                .firstCategory(entity.getFirstCategory())
                .secondCategory(entity.getSecondCategory())
                .title(entity.getTitle())
                .body(entity.getBody())
                .build();
    }
}
