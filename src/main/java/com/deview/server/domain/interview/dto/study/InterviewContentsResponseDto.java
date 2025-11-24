package com.deview.server.domain.interview.dto.study;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InterviewContentsResponseDto {
    private List<InterviewContentAnswer> contents;
}
