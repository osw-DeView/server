package com.deview.server.domain.interview.service;

import com.deview.server.domain.interview.dto.reviews.InterviewReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class InterviewReviewClient {

    private final WebClient fastApiClient;

    public Mono<InterviewReview> getInterviewReviews(String company_name) {
        return fastApiClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/interview-reviews")
                        .queryParam("company_name", company_name)
                        .build())
                .retrieve()
                .bodyToMono(InterviewReview.class);
    }

}
