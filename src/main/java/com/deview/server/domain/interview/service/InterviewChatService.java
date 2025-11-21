package com.deview.server.domain.interview.service;

import com.deview.server.domain.interview.domain.chat.InterviewSession;
import com.deview.server.domain.interview.domain.chat.ChatReview;
import com.deview.server.domain.interview.domain.chat.TurnEvaluation;
import com.deview.server.domain.interview.dto.chat.Message;
import com.deview.server.domain.interview.dto.chat.request.fastapi_request.FastApiEvaluationRequest;
import com.deview.server.domain.interview.dto.chat.request.fastapi_request.FastApiNextRequest;
import com.deview.server.domain.interview.dto.chat.request.InterviewEvaluationRequest;
import com.deview.server.domain.interview.dto.chat.request.InterviewNextRequest;
import com.deview.server.domain.interview.dto.chat.request.InterviewStartRequest;
import com.deview.server.domain.interview.dto.chat.response.InterviewEvaluationResponse;
import com.deview.server.domain.interview.dto.chat.response.InterviewNextResponse;
import com.deview.server.domain.interview.dto.chat.response.InterviewStartResponse;
import com.deview.server.domain.interview.dto.chat.response.record.ChatReviewResponse;
import com.deview.server.domain.interview.dto.chat.response.record.TurnEvaluationResponseDto;
import com.deview.server.domain.interview.repository.chat.InterviewSessionRepository;
import com.deview.server.domain.interview.repository.chat.ChatReviewRepository;
import com.deview.server.global.auth.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Service
@RequiredArgsConstructor
public class InterviewChatService {

    private final WebClient webClient;
    private final InterviewSessionRepository sessionRepository;
    private final ChatReviewRepository chatReviewRepository;
    private final ObjectMapper objectMapper;

    private String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Mono<InterviewStartResponse> startInterview(InterviewStartRequest req) {
        String username = getCurrentUsername();

        // 세션 생성
        InterviewSession newSession = InterviewSession.create(username, req.getInterviewType());

        // 첫 질문 요청
        return webClient.post()
                .uri("/interview/start")
                .bodyValue(req) // InterviewStartRequest는 interviewType과 keyword를 포함
                .retrieve()
                .bodyToMono(InterviewStartResponse.class)
                .doOnSuccess(response -> {
                    // 3. 첫 질문(assistant)을 세션에 추가
                    Message firstQuestion = new Message("assistant", response.getResponse());
                    newSession.addMessage(firstQuestion);

                    // 4. Redis에 세션 저장
                    sessionRepository.save(newSession);
                    log.info("New interview session created in Redis: {}", newSession.getId());

                    // 5. 응답에 세션 ID 추가
                    response.setSessionId(newSession.getId());
                });
    }

    public Mono<InterviewNextResponse> next(InterviewNextRequest req) {
        String sessionId = req.getSessionId();

        // Redis에서 세션 조회
        InterviewSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new NoSuchElementException("세션을 찾을 수 없습니다: " + sessionId));

        // 사용자 답변(user)을 세션에 추가
        Message userMessage = req.getMessages().get(req.getMessages().size() - 1);
        session.addMessage(userMessage);

        // 다음 질문 요청
        FastApiNextRequest fastApiReq = FastApiNextRequest.builder()
                .interviewType(session.getInterviewType())
                .messages(session.getMessages())
                .build();

        return webClient.post()
                .uri("/interview/next")
                .bodyValue(fastApiReq) // FastAPI용 DTO 전송
                .retrieve()
                .bodyToMono(InterviewNextResponse.class)
                .doOnSuccess(response -> {
                    Message nextQuestion = new Message("assistant", response.getResponse());
                    session.addMessage(nextQuestion);

                    sessionRepository.save(session);
                    log.info("Interview session updated in Redis: {}", sessionId);
                });
    }

    @Transactional
    public Mono<InterviewEvaluationResponse> evaluate(InterviewEvaluationRequest req) {
        String sessionId = req.getSessionId();

        InterviewSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new NoSuchElementException("세션을 찾을 수 없습니다: " + sessionId));

        FastApiEvaluationRequest fastApiReq = FastApiEvaluationRequest.builder()
                .conversation(session.getMessages())
                .build();

        return webClient.post()
                .uri("/interview/evaluation")
                .bodyValue(fastApiReq)
                .retrieve()
                .bodyToMono(InterviewEvaluationResponse.class)
                .publishOn(Schedulers.boundedElastic())
                .doOnSuccess(response -> {
                    try {
                        // 최종 평가 결과 DB에 저장
                        String messagesJson = objectMapper.writeValueAsString(session.getMessages());
                        String improvementKeywordsJson = objectMapper.writeValueAsString(response.getEvaluationReport().getImprovementKeywords());

                        ChatReview review = ChatReview.builder()
                                .username(session.getUsername())
                                .interviewType(session.getInterviewType())
                                .messages(messagesJson)
                                .overallScore(response.getEvaluationReport().getOverallScore())
                                .overallFeedback(response.getEvaluationReport().getOverallFeedback())
                                .improvementKeywords(improvementKeywordsJson)
                                .build();

                        // 턴별 평가 저장
                        response.getEvaluationReport().getTurnEvaluations().forEach(turnDto -> {
                            TurnEvaluation turnEvaluation = TurnEvaluation.builder()
                                    .turn(turnDto.getTurn())
                                    .question(turnDto.getQuestion())
                                    .score(turnDto.getScore())
                                    .feedback(turnDto.getFeedback())
                                    .build();
                            review.addTurnEvaluation(turnEvaluation);
                        });

                        chatReviewRepository.save(review);
                        log.info("Saved chat review to DB for session: {}", sessionId);

                        sessionRepository.deleteById(sessionId);
                        log.info("Deleted interview session from Redis: {}", sessionId);

                    } catch (JsonProcessingException e) {
                        log.error("Failed to serialize messages for session: {}", sessionId, e);
                        throw new RuntimeException("메시지 직렬화에 실패했습니다.", e);
                    }
                });
    }

    @Transactional
    public List<ChatReviewResponse> findChatReviewResponses(String token) {

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        String username = JwtUtil.getUsername(token);
        List<ChatReview> chatReviewList = chatReviewRepository.findByUsername(username);

        if(chatReviewList.isEmpty()){
            return List.of();
        }

        return chatReviewList.stream()
                .map(r -> ChatReviewResponse.builder()
                        .id(r.getId())
                        .interviewType(r.getInterviewType())
                        .messages(r.getMessages())
                        .overallScore(r.getOverallScore())
                        .overallFeedback(r.getOverallFeedback())
                        .improvementKeywords(r.getImprovementKeywords())
                        .turnEvaluations(r.getTurnEvaluations().stream()
                                .map(TurnEvaluationResponseDto::from)
                                .collect(Collectors.toList()))
                        .createdAt(r.getCreatedAt().toString())
                        .build())
                .toList();
    }

}