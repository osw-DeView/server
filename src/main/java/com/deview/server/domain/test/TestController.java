package com.deview.server.domain.test;

import com.deview.server.global.redis.RefreshTokenRepository;
import com.deview.server.global.response.ApiResponse;
import com.deview.server.global.response.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Tag(name = "Test", description = "테스트 api")
@RequiredArgsConstructor
public class TestController {

    private final RefreshTokenRepository refreshTokenRepository;

    @PostMapping()
    @Operation(summary = "테스트 API (POST)", description = "테스트용 POST API입니다.")
    public ApiResponse<?> testPost() {
        return ApiResponse.success(Status.OK.getCode(),Status.OK.getMessage(), "Test POST API is working!");
    }

    @GetMapping("/allLogout")
    public ApiResponse<?> allLogout(){
        refreshTokenRepository.deleteAll();
        return ApiResponse.success(Status.OK.getCode(), Status.OK.getMessage(), "모든 리프레시 토큰이 삭제되었습니다.");
    }

}
