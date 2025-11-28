package com.deview.server.domain.interview.dto.community.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BestQnaDto {
    private String question;
    private String answer;
    private String category;
}
