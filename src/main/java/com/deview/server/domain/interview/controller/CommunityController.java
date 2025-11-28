package com.deview.server.domain.interview.controller;

import com.deview.server.domain.interview.dto.community.response.BestQnaListResponseDto;
import com.deview.server.domain.interview.service.CommunityService;
import com.deview.server.global.response.ApiResponse;
import com.deview.server.global.response.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/interview/community")
public class CommunityController {

    private final CommunityService communityService;

    @GetMapping("/best-qna")
    public ApiResponse<BestQnaListResponseDto> getBestQna() {
        BestQnaListResponseDto responseDto = communityService.getBestQna();
        return ApiResponse.success(Status.OK.getCode(), Status.OK.getMessage(), responseDto);
    }
}