package com.deview.server.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum Status {

    //공통 정상 응답 COMMON
    OK(HttpStatus.OK, "COMMON200", "성공입니다."),
    CREATED(HttpStatus.CREATED, "COMMON201", "생성되었습니다."),

    //공통 오류 응답 COMMON
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버에 오류가 발생했습니다."),

    //멤버 관련 오류
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "회원이 존재하지 않습니다."),
    MEMBER_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "MEMBER401", "회원 인증이 되지 않았습니다."),
    LOGIN_FAILED_INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "MEMBER402", "비밀번호가 일치하지 않습니다."),


    // 회원 가입 오류
    ALREADY_EXISTS_USERNAME(HttpStatus.CONFLICT,"MEMBER409","이미 존재하는 아이디입니다."),
    ALREADY_EXISTS_NICKNAME(HttpStatus.CONFLICT,"MEMBER410","이미 존재하는 닉네임입니다."),
    MEMBER_PASSWORD_INVALID(HttpStatus.BAD_REQUEST,"MEMBER400","잘못된 비밀번호 형식입니다."),

    ;

    private final HttpStatus httpStatus;

    private final String code;

    private final String message;

    public Body getBody() {
        return Body.builder()
            .message(message)
            .code(code)
            .isSuccess(httpStatus.is2xxSuccessful())
            .httpStatus(httpStatus)
            .build();
    }
}
