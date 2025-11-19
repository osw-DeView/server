package com.deview.server.domain.interview.controller;

import com.deview.server.domain.interview.dto.reviews.InterviewReview;
import com.deview.server.domain.interview.service.InterviewReviewClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/interview")
public class InterviewReviewController {

    private final InterviewReviewClient client;

    @GetMapping("/reviews")
    public Mono<InterviewReview> getReviews(@RequestParam String company_name) {
        return client.getInterviewReviews(company_name);
    }

}

