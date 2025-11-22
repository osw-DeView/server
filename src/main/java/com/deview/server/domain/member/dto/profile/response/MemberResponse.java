package com.deview.server.domain.member.dto.profile.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponse {
    private String username;
    private String nickname;
    private String role;
}
