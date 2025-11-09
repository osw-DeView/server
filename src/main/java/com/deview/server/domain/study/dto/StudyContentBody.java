package com.deview.server.domain.study.dto;

import com.deview.server.domain.study.domain.StudyContent;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyContentBody {
    @Schema(description = "제목", example = "프로세스의 주소 공간이란?")
    private String title;

    @Schema(description = "내용", example = "프로그램이 CPU에 의해 실행됨 → 프로세스가 생성되고 메모리에 '**프로세스 주소 공간**'이 할당됨...")
    private String body;

    public StudyContentBody(StudyContent studyContent) {
        this.title = studyContent.getTitle();
        this.body = studyContent.getBody();
    }
}