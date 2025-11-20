package com.deview.server.domain.interview.domain.chat;

import com.deview.server.domain.interview.dto.chat.Message;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Getter
@RedisHash("interviewSession")
public class InterviewSession {

    @Id
    private String id;

    private String username;

    private String interviewType;

    private List<Message> messages;

    @TimeToLive(unit = TimeUnit.HOURS)
    private Long timeToLive; // 1시간 후 만료

    private InterviewSession(String username, String interviewType) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.interviewType = interviewType;
        this.messages = new ArrayList<>();
        this.timeToLive = 1L;
    }

    public static InterviewSession create(String username, String interviewType) {
        return new InterviewSession(username, interviewType);
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }
}
