package com.deview.server.content.study.domain;

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
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String studyCntId;

    private String FIRST_CATEGORY;

    @NotNull
    private String SECOND_CATEGORY;

    @NotNull
    private String TITLE;

    @NotNull
    private String BODY;
}
