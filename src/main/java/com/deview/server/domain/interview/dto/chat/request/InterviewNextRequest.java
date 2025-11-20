package com.deview.server.domain.interview.dto.chat.request;

import com.deview.server.domain.interview.dto.chat.Message;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "다음 질문 요청 DTO",
        example = "{\n  \"sessionId\": \"a1b2c3d4-e5f6-7890-1234-567890abcdef\",\n  \"interviewType\": \"CS\",\n  \"messages\": [\n    {\n      \"content\": \"운영체제에서 프로세스와 스레드의 가장 근본적인 차이는 무엇이며, 이 차이가 시스템의 메모리 주소 공간 할당 및 자원 관리 측면에서 어떤 영향을 미치는지 구체적으로 설명해주세요.\",\n      \"role\": \"assistant\"\n    },\n    {\n      \"content\": \"프로세스는 독립된 메모리 공간을 할당받는 실행의 단위이고, 스레드는 프로세스 내에서 자원을 공유하며 실행되는 흐름의 단위입니다. 때문에 프로세스 간 통신(IPC)은 복잡하지만, 같은 프로세스 내의 스레드 간 통신은 메모리를 공유하므로 훨씬 간단하고 빠릅니다.\",\n      \"role\": \"user\"\n    }\n  ]\n}"
)
public class InterviewNextRequest {
    @Schema(description = "면접 세션 고유 ID", example = "a1b2c3d4-e5f6-7890-1234-567890abcdef")
    private String sessionId;

    @Schema(description = "면접 유형", example = "CS")
    private String interviewType;

    @Schema(description = "현재까지의 대화 내용 리스트 (직전 질문/답변 포함)")
    private List<Message> messages;
}