package com.deview.server.domain.study.controller;

import com.deview.server.domain.study.domain.StudyContent;
import com.deview.server.domain.study.dto.CategoryItemDto;
import com.deview.server.domain.study.dto.CategoryListResponseDto;
import com.deview.server.domain.study.dto.StudyContentRequestDto;
import com.deview.server.domain.study.dto.StudyContentResponseDto;
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
     * 상위/하위 카테고리, 제목을 Dto로 받으면 그것을 토대로 Body를 받아와 전부 반환
     *
     * @param requestDto
     * @return ApiResponse<StudyContentResponseDto>
     */
    @PostMapping("/getBody")
    public ApiResponse<StudyContentResponseDto> findStudyContent(@Valid @RequestBody StudyContentRequestDto requestDto) {

        StudyContent contentEntity = studyService.findStudyContentByCriteria(requestDto);

        return ApiResponse.success(Status.OK.getCode(),
                Status.OK.getMessage(), StudyContentResponseDto.fromEntity(contentEntity));
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
