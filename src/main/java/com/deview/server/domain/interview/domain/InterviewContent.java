package com.deview.server.domain.interview.domain;

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
@Table(name = "TECH_INTERVIEW")
public class InterviewContent {

    @Id
    @NotNull
    @Column(name = "TECH_INTERVIEW_ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long techInterviewId;

    @Column(name = "HGH_QST_NUM")
    private Long highQstNum;

    @NotNull
    @Column(name = "CATEGORY")
    private String category;

    @NotNull
    @Column(name = "KEYWORD")
    private String keyword;

    @NotNull
    @Column(name = "QUESTION")
    private String question;

    @NotNull
    @Column(name = "ANSWER")
    private String answer;

    @NotNull
    @Column(name = "HGH_QST_YN")
    private String highQstYn;
}
