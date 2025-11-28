package com.deview.server.domain.interview.repository.chat;

import com.deview.server.domain.interview.domain.chat.TurnEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TurnEvaluationRepository extends JpaRepository<TurnEvaluation, Long> {
    /**
     * 점수가 특정 기준 이상인 모든 TurnEvaluation을 연관된 ChatReview와 함께 조회합니다.
     * N+1 쿼리 문제를 방지하기 위해 JOIN FETCH를 사용합니다.
     * @param score 점수 기준
     * @return 점수 기준을 만족하는 TurnEvaluation 리스트
     */
    @Query("SELECT te FROM TurnEvaluation te JOIN FETCH te.chatReview WHERE te.score >= :score")
    List<TurnEvaluation> findByScoreGreaterThanEqualWithChatReview(@Param("score") int score);
}
