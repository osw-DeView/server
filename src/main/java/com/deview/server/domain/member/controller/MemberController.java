package com.deview.server.domain.member.controller;

import com.deview.server.domain.auth.dto.request.LoginRequestDto;
import com.deview.server.domain.auth.service.AuthService;
import com.deview.server.domain.member.dto.IsExistanceMamber;
import com.deview.server.domain.member.dto.profile.response.MemberResponse;
import com.deview.server.domain.member.service.MemberService;
import com.deview.server.global.response.ApiResponse;
import com.deview.server.global.response.Status;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final AuthService authService;

    @PostMapping("/exist")
    @Operation(summary = "멤버 존재 여부 확인", description = "access 토근 인증 후 멤버 존재 여부 확인")
    public ApiResponse<IsExistanceMamber> memberExist(@RequestBody @Valid LoginRequestDto dto){
        IsExistanceMamber res = new IsExistanceMamber(authService.isExistMember(dto));

        if(res.getIsExist()){
            return ApiResponse.success(Status.OK.getCode(),
                    Status.CREATED.getMessage(), res);
        }
        return ApiResponse.onFailure(Status.MEMBER_NOT_FOUND.getCode(),
                Status.MEMBER_NOT_FOUND.getMessage(), res);

    }


    @GetMapping("/profile")
    public ApiResponse<MemberResponse> getMyInfo(@RequestHeader("Authorization") String token) {
        MemberResponse memberResponse = authService.memberProfile(token);

        return ApiResponse.success(
                Status.OK.getCode(),
                Status.OK.getMessage(),
                memberResponse
        );
    }
}
