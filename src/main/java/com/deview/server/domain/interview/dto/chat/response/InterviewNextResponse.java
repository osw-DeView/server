package com.deview.server.domain.interview.dto.chat.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InterviewNextResponse {
    @Schema(description = "다음 면접 질문 또는 코멘트", example = "좋은 답변입니다. 그렇다면 스레드 간 동기화 문제를 해결하기 위한 구체적인 기법에는 어떤 것들이 있나요?")
    private String response;
}
