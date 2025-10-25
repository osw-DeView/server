package com.deview.server.domain.member.controller;

import com.deview.server.domain.auth.dto.request.LoginRequestDto;
import com.deview.server.domain.auth.service.AuthService;
import com.deview.server.domain.member.dto.IsExistanceMamber;
import com.deview.server.global.response.ApiResponse;
import com.deview.server.global.response.Status;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final AuthService authService;

    @PostMapping("test/member/exist")
    public ApiResponse<IsExistanceMamber> memberExist(@RequestBody @Valid LoginRequestDto dto){
        IsExistanceMamber res = new IsExistanceMamber(authService.isExistMember(dto));

        if(res.getIsExist()){
            return ApiResponse.success(Status.OK.getCode(),
                    Status.CREATED.getMessage(), res);
        }
        return ApiResponse.onFailure(Status.MEMBER_NOT_FOUND.getCode(),
                Status.MEMBER_NOT_FOUND.getMessage(), res);

    }
}
