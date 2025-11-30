package com.deview.server.domain.interview.service;

import com.deview.server.domain.interview.domain.chat.TurnEvaluation;
import com.deview.server.domain.interview.dto.chat.Message;
import com.deview.server.domain.interview.dto.community.response.BestQnaDto;
import com.deview.server.domain.interview.dto.community.response.BestQnaListResponseDto;
import com.deview.server.domain.interview.repository.chat.TurnEvaluationRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityService {

    private final TurnEvaluationRepository turnEvaluationRepository;
    private final ObjectMapper objectMapper;
    private static final int SCORE_THRESHOLD = 75;

    public BestQnaListResponseDto getBestQna() {
        List<TurnEvaluation> highScoringTurns = turnEvaluationRepository.findByScoreGreaterThanEqualWithChatReview(SCORE_THRESHOLD);
        List<BestQnaDto> bestQnas = new ArrayList<>();

        for (TurnEvaluation turn : highScoringTurns) {
            try {
                String messagesJson = turn.getChatReview().getMessages();
                List<Message> messages = objectMapper.readValue(messagesJson, new TypeReference<>() {});

                int answerIndex = (turn.getTurn() * 2) - 1;

                if (answerIndex >= 0 && answerIndex < messages.size()) {
                    Message answerMessage = messages.get(answerIndex);
                    // 해당 메시지가 'user'의 답변인지 확인하여 안정성 확보
                    if ("user".equals(answerMessage.getRole())) {
                        String questionText = turn.getQuestion();
                        String interviewType = turn.getChatReview().getInterviewType();
                        String category = mapCategory(interviewType); // 카테고리 매핑

                        bestQnas.add(new BestQnaDto(questionText, answerMessage.getContent(), category));
                    }
                }
            } catch (IOException e) {
                log.error("Failed to parse messages JSON for chatReviewId: {}", turn.getChatReview().getId(), e);

            }
        }
        return new BestQnaListResponseDto(bestQnas);
    }

    private String mapCategory(String interviewType) {
        return switch (interviewType) {
            case "Operating System" -> "운영체제" ;
            case "NetWork" -> "네트워크";
            case "Database" -> "데이터베이스";
            case "Computer Architecture" -> "컴퓨터구조";
            case "SoftWare Engineering" -> "소프트웨어공학";
            case "Data Structure" -> "자료구조";
            default -> "기타";
        };
    }
}
