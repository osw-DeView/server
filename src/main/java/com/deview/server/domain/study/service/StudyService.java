package com.deview.server.domain.study.service;

import com.deview.server.domain.study.domain.StudyContent;
import com.deview.server.domain.study.dto.StudyContentRequestDto;
import com.deview.server.domain.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudyService {
    private final StudyRepository studyRepository;

    public StudyContent findStudyContentByCriteria(StudyContentRequestDto dto) {
        String firstCategory = dto.getFirstCategory();
        String secondCategory = dto.getSecondCategory();
        String title = dto.getTitle();

        return studyRepository.findByFirstCategoryAndSecondCategoryAndTitle(
                        firstCategory,
                        secondCategory,
                        title
                ).orElseThrow(() -> new IllegalArgumentException("해당 조건의 학습 컨텐츠를 찾을 수 없습니다."));
    }


}
