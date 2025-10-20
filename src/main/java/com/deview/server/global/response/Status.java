package com.deview.server.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum Status {

    //공통 정상 응답 COMMON
    OK(HttpStatus.OK, "COMMON200", "성공입니다."),

    //공통 오류 응답 COMMON
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버에 오류가 발생했습니다."),
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
