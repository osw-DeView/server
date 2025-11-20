package com.deview.server.domain.interview.dto.chat.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class InterviewStartRequest {
    @Schema(description = "면접 유형", example = "CS")
    private String interviewType;

}
