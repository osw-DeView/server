package com.deview.server.domain.interview.dto.chat.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "면접 평가 요청 DTO. 세션 ID만 전달하면 서버에 저장된 전체 대화 내용으로 평가가 진행됩니다.",
        example = "{\n  \"sessionId\": \"a1b2c3d4-e5f6-7890-1234-567890abcdef\"\n}"
)
public class InterviewEvaluationRequest {
    @Schema(description = "면접 세션 고유 ID", example = "a1b2c3d4-e5f6-7890-1234-567890abcdef")
    private String sessionId;
}
