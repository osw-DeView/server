package com.deview.server.domain.study.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STUDY_CONTENT")
public class StudyContent {

    @Id
    @NotNull
    @Column(name = "STUDY_CONTENT_ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyContentId;

    @Column(name = "FIRST_CATEGORY")
    private String firstCategory;

    @NotNull
    @Column(name = "SECOND_CATEGORY")
    private String secondCategory;

    @NotNull
    @Column(name = "TITLE")
    private String title;

    @NotNull
    @Column(name = "BODY")
    private String body;
}
