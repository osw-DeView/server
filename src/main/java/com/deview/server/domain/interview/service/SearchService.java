package com.deview.server.domain.interview.service;

import com.deview.server.domain.interview.dto.keyword.response.GoogleSearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${google.search.api-key}")
    private String apiKey;

    @Value("${google.search.cx}")
    private String searchEngineId;

    private static final String GOOGLE_API_URL = "https://www.googleapis.com/customsearch/v1";

    public List<GoogleSearchResponse.Item> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return Collections.emptyList();
        }

        try {
            // 1. 검색어 튜닝 (면접 학습용으로 최적화)
            String query = keyword + " 개념";

            // 2. URI 생성
            URI uri = UriComponentsBuilder.fromHttpUrl(GOOGLE_API_URL)
                    .queryParam("key", apiKey)
                    .queryParam("cx", searchEngineId)
                    .queryParam("q", query)
                    .queryParam("num", 3) // 상위 3개만 조회
                    .queryParam("hl", "ko") // 한국어 결과 우선
                    .build()
                    .toUri();

            // 3. API 호출
            GoogleSearchResponse response = restTemplate.getForObject(uri, GoogleSearchResponse.class);

            // 4. 결과 반환
            if (response != null && response.getItems() != null) {
                return response.getItems();
            }

        } catch (Exception e) {
            log.error("Google Search API 호출 실패: {}", e.getMessage());
            // 실제 운영 시에는 에러 발생 시 빈 리스트를 반환하거나 예외를 던져 처리
        }

        return Collections.emptyList();
    }
}
