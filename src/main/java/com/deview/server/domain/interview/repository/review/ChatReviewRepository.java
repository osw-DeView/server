package com.deview.server.domain.interview.repository.review;

import com.deview.server.domain.interview.domain.chat.ChatReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatReviewRepository extends JpaRepository<ChatReview, Long> {
}
