package com.deview.server.domain.interview.controller;

import com.deview.server.domain.interview.domain.InterviewContent;
import com.deview.server.domain.interview.dto.InterviewCategoryItemDto;
import com.deview.server.domain.interview.dto.InterviewCategoryListResponseDto;
import com.deview.server.domain.interview.dto.InterviewContentRequestDto;
import com.deview.server.domain.interview.dto.InterviewContentResponseDto;
import com.deview.server.domain.interview.service.InterviewService;
import com.deview.server.domain.study.domain.StudyContent;
import com.deview.server.domain.study.dto.CategoryItemDto;
import com.deview.server.domain.study.dto.CategoryListResponseDto;
import com.deview.server.domain.study.dto.StudyContentRequestDto;
import com.deview.server.domain.study.dto.StudyContentResponseDto;
import com.deview.server.global.response.ApiResponse;
import com.deview.server.global.response.Status;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/interview")
public class InterviewController {
        private final InterviewService interviewService;

    /**
     * 기술면접컨텐츠 내용 조회
     * 카테고리, 키워드, 질문을 Dto로 받으면 그것을 토대로 답변을 받아와 전부 반환
     *
     * @param requestDto
     * @return ApiResponse<StudyContentResponseDto>
     */
    @PostMapping("/getAnswer")
    public ApiResponse<InterviewContentResponseDto> findInterviewContent(@Valid @RequestBody InterviewContentRequestDto requestDto) {

        InterviewContent contentEntity = interviewService.findInterviewContent(requestDto);

        return ApiResponse.success(Status.OK.getCode(),
                Status.OK.getMessage(), InterviewContentResponseDto.fromEntity(contentEntity));
    }

    /**
     * 카테고리 목록 조회
     *
     * @return ApiResponse<InterviewCategoryListResponseDto>
     */
    @GetMapping("/categories")
    public ApiResponse<InterviewCategoryListResponseDto> getAllCategories() {

        List<InterviewCategoryItemDto> tocItems = interviewService.getCategoryToc();

        InterviewCategoryListResponseDto responseData = new InterviewCategoryListResponseDto(tocItems);

        return ApiResponse.success(Status.OK.getCode(),
                Status.OK.getMessage(), responseData);
    }
}
