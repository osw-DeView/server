package com.deview.server.domain.study.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudyContentsResponseDto {
    private List<StudyContentBody> contents;
}