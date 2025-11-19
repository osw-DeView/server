package com.deview.server.domain.interview.dto.reviews;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Question {
    private String question;
    private String answer;
    private List<QnAPair> qna_pairs;

}