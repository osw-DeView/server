package com.deview.server.domain.interview.domain.chat;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "turn_evaluation")
public class TurnEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int turn;

    @Column(columnDefinition = "TEXT")
    private String question;

    private int score;

    @Column(columnDefinition = "TEXT")
    private String feedback;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_review_id")
    private ChatReview chatReview;

    @Builder
    public TurnEvaluation(int turn, String question, int score, String feedback, ChatReview chatReview) {
        this.turn = turn;
        this.question = question;
        this.score = score;
        this.feedback = feedback;
        this.chatReview = chatReview;
    }

    public void setChatReview(ChatReview chatReview) {
        this.chatReview = chatReview;
    }
}
