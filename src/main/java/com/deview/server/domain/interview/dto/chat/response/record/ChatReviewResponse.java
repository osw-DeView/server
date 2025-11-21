package com.deview.server.domain.interview.dto.chat.response.record;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ChatReviewResponse {

    private Long id;
    private String interviewType;
    private String messages;
    private int overallScore;
    private String overallFeedback;
    private String improvementKeywords;
    private List<TurnEvaluationResponseDto> turnEvaluations;
    private String createdAt;

    @Builder
    public ChatReviewResponse(Long id, String interviewType, String messages,
                              int overallScore, String overallFeedback, String improvementKeywords,
                              List<TurnEvaluationResponseDto> turnEvaluations, String createdAt) {
        this.id = id;
        this.interviewType = interviewType;
        this.messages = messages;
        this.overallScore = overallScore;
        this.overallFeedback = overallFeedback;
        this.improvementKeywords = improvementKeywords;
        this.turnEvaluations = turnEvaluations;
        this.createdAt = createdAt;
    }
}