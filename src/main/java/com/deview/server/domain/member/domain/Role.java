package com.deview.server.domain.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    USER("ROLE_USER","유저"),
    ADMIN("ROLE_AMDIN","운영진")
    ;

    private String key;
    private String description;
}
