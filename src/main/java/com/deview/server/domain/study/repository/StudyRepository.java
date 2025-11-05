package com.deview.server.domain.study.repository;

import com.deview.server.domain.study.domain.StudyContent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudyRepository extends JpaRepository<StudyContent, String> {

    Optional<StudyContent> findByFirstCategoryAndSecondCategoryAndTitle(
            String firstCategory,
            String secondCategory,
            String title
    );
}
