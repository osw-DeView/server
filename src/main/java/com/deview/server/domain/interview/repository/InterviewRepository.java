package com.deview.server.domain.interview.repository;

import com.deview.server.domain.interview.domain.InterviewContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InterviewRepository extends JpaRepository<InterviewContent, String> {

    /**
     * 카테고리, 키워드 조합으로 원하는 기술면접 조회
     */
    @Query("SELECT i FROM InterviewContent i WHERE i.category = :category " +
            "AND i.keyword = :keyword " +
            "AND i.question = :question")
    Optional<InterviewContent> findByCatAndKwd(
            @Param("category") String category,
            @Param("keyword") String keyword
    );

    /**
     * DB에서 중복을 제거한 Category 목록만 조회
     */
    @Query("SELECT DISTINCT i.category FROM InterviewContent i")
    List<String> findCategory();


    /**
     * 특정 Category에 속하는 중복 제거된 keyword 목록을 조회
     */
    @Query("SELECT DISTINCT i.keyword FROM InterviewContent i WHERE i.category = :category")
    List<String> findKeyword(
            @Param("category") String category
    );
}
