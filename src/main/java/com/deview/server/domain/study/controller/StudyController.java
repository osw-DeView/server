package com.deview.server.domain.study.controller;

import com.deview.server.domain.study.dto.*;
import com.deview.server.domain.study.service.StudyService;
import com.deview.server.global.response.ApiResponse;
import com.deview.server.global.response.Status;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/study")
public class StudyController {
    private final StudyService studyService;

    /**
     * 학습컨텐츠 내용 조회
     * 상위/하위 카테고리를 Dto로 받으면 그것을 토대로 해당하는 제목과 본문을 전부 반환
     *
     * @param requestDto
     * @return ApiResponse<StudyContentsResponseDto>
     */
    @PostMapping("/contents")
    public ApiResponse<StudyContentsResponseDto> findStudyContents(@Valid @RequestBody StudyContentRequestDto requestDto) {

        List<StudyContentBody> contentBodies = studyService.findStudyContentsByCategory(requestDto);
        StudyContentsResponseDto responseData = new StudyContentsResponseDto(contentBodies);

        return ApiResponse.success(Status.OK.getCode(),
                Status.OK.getMessage(), responseData);
    }

    /**
     * 카테고리 목록 조회
     *
     * @return ApiResponse<StudyContentResponseDto>
     */
    @GetMapping("/categories")
    public ApiResponse<CategoryListResponseDto> getAllCategories() {

        List<CategoryItemDto> tocItems = studyService.getCategoryToc();

        CategoryListResponseDto responseData = new CategoryListResponseDto(tocItems);

        return ApiResponse.success(Status.OK.getCode(),
                Status.OK.getMessage(), responseData);
    }
}