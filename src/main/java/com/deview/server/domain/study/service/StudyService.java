package com.deview.server.domain.study.service;

import com.deview.server.domain.study.domain.StudyContent;
import com.deview.server.domain.study.dto.CategoryItemDto;
import com.deview.server.domain.study.dto.StudyContentBody;
import com.deview.server.domain.study.dto.StudyContentRequestDto;
import com.deview.server.domain.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyService {
    private final StudyRepository studyRepository;

    /**
     * 전달하고자 하는 학습 컨텐츠 조회
     */
    public List<StudyContentBody> findStudyContentsByCategory(StudyContentRequestDto dto) {
        List<StudyContent> contents = studyRepository.findAllByFirstCategoryAndSecondCategory(
                dto.getFirstCategory(),
                dto.getSecondCategory()
        );

        return contents.stream()
                .map(StudyContentBody::new)
                .collect(Collectors.toList());
    }

    /**
     * 목차 구성을 위한 전체 카테고리 계층 구조 조회
     */
    public List<CategoryItemDto> getCategoryToc() {

        //모든 firstCategory 목록을 중복 없이 가져옴
        List<String> firstCategories = studyRepository.findFirstCategories();
        List<CategoryItemDto> categoryList = new ArrayList<>();

        for (String firstCat : firstCategories) {

            //해당 firstCategory에 속한 secondCategory 목록을 DB에서 가져옴
            List<String> secondCategories =
                    studyRepository.findSecondCategories(firstCat);

            //DTO 생성 (ex. "Operating System", ["운영체제", "프로세스 & 스레드"...] )
            categoryList.add(new CategoryItemDto(firstCat, secondCategories));
        }

        return categoryList;
    }
}
