package com.deview.server.domain.interview.dto.chat.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class InterviewStartResponse {
    @Schema(description = "첫 번째 면접 질문", example = "데이터베이스 정규화의 목적은 무엇이며, 정규화를 통해 얻을 수 있는 장점과 단점에 대해 설명해주세요.") // 추가
    private String response;
    @Schema(description = "면접 세션 고유 ID", example = "a1b2c3d4-e5f6-7890-1234-567890abcdef")
    private String sessionId;
}

