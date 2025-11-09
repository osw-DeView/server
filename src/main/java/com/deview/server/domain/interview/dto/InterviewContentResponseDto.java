package com.deview.server.domain.interview.dto;

import com.deview.server.domain.interview.domain.InterviewContent;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewContentResponseDto {

    @Schema(description = "질문", example = "오라클 시퀀스(Oracle Sequence) 는 무엇인가요?")
    private String question;

    @Schema(description = "답변", example = "UNIQUE한 값을 생성해주는 오라클 객체. 시퀀스를 생성하면 PK와 같이 순차적으로 증가하는 컬럼을 자동 생성할 수 있다.")
    private String answer;

    @Schema(description = "상위질문사용가능", example = "TRUE")
    private String highQstYn;

    public static InterviewContentResponseDto fromEntity(InterviewContent entity) {
        return InterviewContentResponseDto.builder()
                .question(entity.getQuestion())
                .answer(entity.getAnswer())
                .highQstYn(entity.getHighQstYn())
                .build();
    }
}
