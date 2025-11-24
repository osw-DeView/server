package com.deview.server.domain.interview.controller;

import com.deview.server.domain.interview.domain.chat.ChatReview;
import com.deview.server.domain.interview.dto.chat.request.InterviewEvaluationRequest;
import com.deview.server.domain.interview.dto.chat.request.InterviewNextRequest;
import com.deview.server.domain.interview.dto.chat.request.InterviewStartRequest; // 사용
import com.deview.server.domain.interview.dto.chat.response.InterviewEvaluationResponse;
import com.deview.server.domain.interview.dto.chat.response.InterviewNextResponse;
import com.deview.server.domain.interview.dto.chat.response.InterviewStartResponse;
import com.deview.server.domain.interview.dto.chat.response.record.ChatReviewResponse;
import com.deview.server.domain.interview.service.InterviewChatService;
import com.deview.server.global.auth.util.JwtUtil;
import com.deview.server.global.response.ApiResponse;
import com.deview.server.global.response.Status;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/interview/chat")
public class InterviewChatController {

    private final InterviewChatService interviewChatService;

    @PostMapping("/start")
    public Mono<InterviewStartResponse> start(@RequestBody InterviewStartRequest req) {
        return interviewChatService.startInterview(req);
    }

    @PostMapping("/next")
    public Mono<InterviewNextResponse> next(@RequestBody InterviewNextRequest req) {
        return interviewChatService.next(req);
    }

    @PostMapping("/evaluation")
    public Mono<InterviewEvaluationResponse> evaluate(@RequestBody InterviewEvaluationRequest req) {
        return interviewChatService.evaluate(req);
    }

    @PostMapping("/record")
    public ApiResponse<List<ChatReviewResponse>> record(@RequestHeader("Authorization") String token) {
        return ApiResponse.success(
                Status.OK.getCode(),
                Status.OK.getMessage(),
                interviewChatService.findChatReviewResponses(token)
        );
    }

}