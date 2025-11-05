package com.deview.server.domain.study.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@Getter
@AllArgsConstructor
public class CategoryListResponseDto {
        private List<CategoryItemDto> categories;
}
