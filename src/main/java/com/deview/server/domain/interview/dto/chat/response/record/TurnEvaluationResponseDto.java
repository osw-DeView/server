package com.deview.server.domain.interview.dto.chat.response.record;

import com.deview.server.domain.interview.domain.chat.TurnEvaluation;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TurnEvaluationResponseDto {
    private int turn;
    private String question;
    private int score;
    private String feedback;

    @Builder
    public TurnEvaluationResponseDto(int turn, String question, int score, String feedback) {
        this.turn = turn;
        this.question = question;
        this.score = score;
        this.feedback = feedback;
    }

    public static TurnEvaluationResponseDto from(TurnEvaluation turnEvaluation) {
        return TurnEvaluationResponseDto.builder()
                .turn(turnEvaluation.getTurn())
                .question(turnEvaluation.getQuestion())
                .score(turnEvaluation.getScore())
                .feedback(turnEvaluation.getFeedback())
                .build();
    }
}
