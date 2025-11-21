package com.deview.server.domain.interview.dto.chat.response.record;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatReviewResponse {

    private Long id;
    private String interviewType;
    private String messages;
    private int overallScore;
    private String overallFeedback;
    private String createdAt;

    @Builder
    public ChatReviewResponse(Long id, String interviewType, String messages,
                              int overallScore, String overallFeedback,
                              String createdAt) {
        this.id = id;
        this.interviewType = interviewType;
        this.messages = messages;
        this.overallScore = overallScore;
        this.overallFeedback = overallFeedback;
        this.createdAt = createdAt;
    }
}