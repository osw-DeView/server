package com.deview.server.domain.interview.controller;

import com.deview.server.domain.interview.dto.study.InterviewCategoryItemDto;
import com.deview.server.domain.interview.dto.study.InterviewCategoryListResponseDto;
import com.deview.server.domain.interview.dto.study.InterviewContentAnswer;
import com.deview.server.domain.interview.dto.study.InterviewContentRequestDto;
import com.deview.server.domain.interview.dto.study.InterviewContentsResponseDto;
import com.deview.server.domain.interview.service.InterviewService;
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
     * 카테고리, 키워드를 Dto로 받으면 그것을 토대로 해당하는 질문, 답변, 상위질문가능여부를 받아와 전부 반환
     *
     * @param requestDto
     * @return ApiResponse<InterviewContentAnswer>
     */
    @PostMapping("/answers")
    public ApiResponse<InterviewContentsResponseDto> findInterviewContents(@Valid @RequestBody InterviewContentRequestDto requestDto) {

        List<InterviewContentAnswer> contensAnswers = interviewService.findInterviewContent(requestDto);
        InterviewContentsResponseDto responseData = new InterviewContentsResponseDto(contensAnswers);

        return ApiResponse.success(Status.OK.getCode(),
                Status.OK.getMessage(), responseData);
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
