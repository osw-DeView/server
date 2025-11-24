package com.deview.server.domain.study.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryItemDto {
    @NotBlank
    @Schema(
            description = "상위 카테고리",
            allowableValues = {"Operating System", "Computer Architecture", "Data Structure",
                    "Database", "NetWork", "SoftWare Engineering", "Web"},
            example = "Data Structure" // 대표 예시
    )
    private String firstCategory;           //상위카테고리

    @Schema(
            description = "하위 카테고리 목록",
            example = "[\"Array vs ArrayList vs LinkedList\", \"B Tree & B+ Tree\", \"이진탐색트리 (Binary Search Tree)\", \"해시(Hash)\", \"힙(Heap)\", \"Linked List\", \"자료구조\", \"Stack & Queue\"]"
    )
    private List<String> secondCategory;    //하위카테고리 목록
}
