package com.deview.server.domain.interview.service;

import com.deview.server.domain.interview.domain.InterviewContent;
import com.deview.server.domain.interview.dto.InterviewCategoryItemDto;
import com.deview.server.domain.interview.dto.InterviewContentRequestDto;
import com.deview.server.domain.interview.repository.InterviewRepository;
import com.deview.server.domain.study.dto.CategoryItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InterviewService {

    private final InterviewRepository interviewRepository;

    /**
     * 전달하고자 하는 기술면접 컨텐츠 조회
     */
    public InterviewContent findInterviewContent(InterviewContentRequestDto dto) {
        String category = dto.getCategory();
        String keyword = dto.getKeyword();

        return interviewRepository.findByCatAndKwd(
                category,
                keyword
        ).orElseThrow(() -> new IllegalArgumentException("해당 조건의 기술면접 컨텐츠를 찾을 수 없습니다."));
    }

    /**
     * 목차 구성을 위한 전체 카테고리 계층 구조 조회
     */
    public List<InterviewCategoryItemDto> getCategoryToc() {

        //모든 Category 목록을 중복 없이 가져옴
        List<String> categories = interviewRepository.findCategory();
        List<InterviewCategoryItemDto> categoryList = new ArrayList<>();

        for (String cat : categories) {

            //해당 Category에 속한 keword 목록을 DB에서 가져옴
            List<String> keywords =
                    interviewRepository.findKeyword(cat);

            //DTO 생성 (ex. "Network", ["HTTP&HTTPS", "SSL&TLS"...] )
            categoryList.add(new InterviewCategoryItemDto(cat, keywords));
        }
        return categoryList;
    }
}
