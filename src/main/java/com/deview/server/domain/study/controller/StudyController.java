package com.deview.server.domain.study.controller;

import com.deview.server.domain.study.domain.StudyContent;
import com.deview.server.domain.study.dto.StudyContentRequestDto;
import com.deview.server.domain.study.dto.StudyContentResponseDto;
import com.deview.server.domain.study.service.StudyService;
import com.deview.server.global.response.ApiResponse;
import com.deview.server.global.response.Status;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/study")
public class StudyController {
    private final StudyService studyService;

    @PostMapping("/get")
    public ApiResponse<StudyContentResponseDto> findStudyContent(@Valid @RequestBody StudyContentRequestDto requestDto) {

        StudyContent contentEntity = studyService.findStudyContentByCriteria(requestDto);

        return ApiResponse.success(Status.OK.getCode(),
                Status.OK.getMessage(), StudyContentResponseDto.fromEntity(contentEntity));
    }
}
