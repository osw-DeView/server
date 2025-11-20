package com.deview.server.domain.interview.dto.reviews;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterviewReview {
    private String company_name;
    private List<Review> reviews;
    private int total_reviews;

}