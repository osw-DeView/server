package com.deview.server.domain.interview.dto.keyword.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleSearchResponse {

    private List<Item> items; // 검색 결과 리스트

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        private String title;   // 제목
        private String link;    // URL 링크
        private String snippet; // 요약 내용
        private Pagemap pagemap; // 썸네일 이미지 추출용 (선택사항)
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Pagemap {
        private List<CseImage> cse_image;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CseImage {
        private String src; // 썸네일 URL
    }
}
