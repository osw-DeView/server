package com.deview.server.domain.study.repository;

import com.deview.server.domain.study.domain.StudyContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudyRepository extends JpaRepository<StudyContent, String> {

    /**
     * 상위카테고리, 하위카테고리, 제목 조합으로 원하는 학습컨텐츠 조회
     */
    @Query("SELECT s FROM StudyContent s WHERE s.firstCategory = :firstCat " +
            "AND s.secondCategory = :secondCat " +
            "AND s.title = :title")
    Optional<StudyContent> findByCategoriesAndTitle(
            @Param("firstCat") String firstCategory,
            @Param("secondCat") String secondCategory,
            @Param("title") String title
    );

    List<StudyContent> findAllByFirstCategoryAndSecondCategory(String firstCategory, String secondCategory);

    /**
     * DB에서 중복을 제거한 firstCategory 목록만 조회
     */
    @Query("SELECT DISTINCT sc.firstCategory FROM StudyContent sc")
    List<String> findFirstCategories();


    /**
     * 특정 firstCategory에 속하는 중복 제거된 secondCategory 목록을 조회
     */
    @Query("SELECT DISTINCT sc.secondCategory FROM StudyContent sc WHERE sc.firstCategory = :firstCategory")
    List<String> findSecondCategories(
            @Param("firstCategory") String firstCategory
    );
}