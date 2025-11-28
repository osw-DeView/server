package com.deview.server.domain.interview.dto.chat.response.interview;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterviewReview {
    private String company_name;
    private List<Review> reviews;
    private int jobkorea_count;
    private int saramin_count;
    private int total_reviews;

}