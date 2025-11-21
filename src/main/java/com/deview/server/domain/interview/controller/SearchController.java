package com.deview.server.domain.interview.controller;

import com.deview.server.domain.interview.dto.keyword.response.GoogleSearchResponse;
import com.deview.server.domain.interview.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @Operation(summary = "키워드 기반 학습 자료 검색", description = "Google Custom Search API를 사용하여 관련 블로그/문서를 찾아줍니다.")
    @GetMapping
    public List<GoogleSearchResponse.Item> searchResources(
            @Parameter(description = "검색할 기술 키워드 (예: DBMS, Request Methods)", example = "DBMS N+1")
            @RequestParam String keyword
    ) {
        return searchService.search(keyword);
    }
}
