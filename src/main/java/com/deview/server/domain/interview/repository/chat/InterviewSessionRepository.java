package com.deview.server.domain.interview.repository.chat;

import com.deview.server.domain.interview.domain.chat.InterviewSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewSessionRepository extends CrudRepository<InterviewSession, String> {
}
