package com.deview.server.domain.interview.dto.chat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Schema(description = "메시지 역할 (assistant: 면접관 질문, user: 사용자 답변)", example = "assistant")
    private String role;

    @Schema(description = "메시지 내용 (질문 또는 답변)", example = "프로세스와 스레드의 차이를 설명해주세요.")
    private String content;
}
