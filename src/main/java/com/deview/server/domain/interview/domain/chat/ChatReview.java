package com.deview.server.domain.interview.domain.chat;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chat_review")
public class ChatReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String interviewType;

    @Column(columnDefinition = "TEXT")
    private String messages; // JSON-serialized List<Message>

    private int overallScore;

    @Column(columnDefinition = "TEXT")
    private String overallFeedback;

    @Column(columnDefinition = "TEXT")
    private String improvementKeywords; // JSON-serialized List<String>

    @OneToMany(mappedBy = "chatReview", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TurnEvaluation> turnEvaluations = new ArrayList<>();

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Builder
    private ChatReview(String username, String interviewType, String messages, int overallScore, String overallFeedback, String improvementKeywords) {
        this.username = username;
        this.interviewType = interviewType;
        this.messages = messages;
        this.overallScore = overallScore;
        this.overallFeedback = overallFeedback;
        this.improvementKeywords = improvementKeywords;
    }

    public void addTurnEvaluation(TurnEvaluation turnEvaluation) {
        turnEvaluations.add(turnEvaluation);
        turnEvaluation.setChatReview(this);
    }
}
