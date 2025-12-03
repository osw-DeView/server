package com.deview.server.domain.interview.dto.chat.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Schema(description = "면접 평가 보고서 응답 DTO")
public class InterviewEvaluationResponse {
    @JsonProperty("interviewType")
    @Schema(description = "면접 유형", example = "Operating System")
    private String interviewType;

    @JsonProperty("evaluation_report")
    @Schema(description = "면접 전체 평가 보고서")
    private EvaluationReport evaluationReport;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "전체 평가 보고서의 상세 내용")
    public static class EvaluationReport {
        @JsonProperty("improvement_keywords")
        @Schema(description = "향상시키면 좋을 핵심 키워드 목록", example = "[\"임계 구역(Critical Section)\", \"스핀락(Spinlock)\"]")
        private List<String> improvementKeywords;

        @JsonProperty("overall_feedback")
        @Schema(description = "전반적인 총평", example = "전반적으로 운영체제의 기본 개념이 매우 탄탄합니다.")
        private String overallFeedback;

        @JsonProperty("overall_score")
        @Schema(description = "전체 점수", example = "85")
        private int overallScore;

        @JsonProperty("turn_evaluations")
        @Schema(description = "턴(Turn)별 상세 평가 목록")
        private List<TurnEvaluation> turnEvaluations;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "턴(Turn)별 상세 평가")
    public static class TurnEvaluation {
        @Schema(description = "해당 턴에 대한 피드백", example = "핵심 차이인 '독립된 메모리 공간'과 '자원 공유'를 명확하게 설명했습니다.")
        private String feedback;

        @Schema(description = "해당 턴의 질문 내용 (면접관의 질문)", example = "운영체제에서 프로세스와 스레드의 가장 근본적인 차이는 무엇인가요?")
        private String question;

        @Schema(description = "해당 턴의 점수", example = "90")
        private int score;

        @Schema(description = "해당 턴의 순서 번호", example = "1")
        private int turn;
    }
}