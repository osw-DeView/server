package com.deview.server.content.study.repository;

import com.deview.server.content.study.domain.StudyContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<StudyContent, String> {
}
