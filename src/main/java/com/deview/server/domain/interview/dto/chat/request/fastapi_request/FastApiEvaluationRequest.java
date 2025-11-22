package com.deview.server.domain.interview.dto.chat.request.fastapi_request;

import com.deview.server.domain.interview.dto.chat.Message;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FastApiEvaluationRequest {
    @JsonProperty("conversation")
    private List<Message> conversation;
}
