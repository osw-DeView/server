package com.deview.server.domain.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class InterviewCategoryListResponseDto {

    private List<InterviewCategoryItemDto> categories;
}
