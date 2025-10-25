package com.deview.server.domain.auth.service;


import com.deview.server.domain.auth.dto.request.SignupRequestDto;
import com.deview.server.domain.auth.util.PasswordManager;
import com.deview.server.domain.member.service.MemberService;
import com.deview.server.global.response.GeneralException;
import com.deview.server.global.response.Status;

enum SignupValidationStatus {


	PASSWORD_INVALID {
		@Override
		public void validate(SignupRequestDto signupRequestDto, MemberService memberService, PasswordManager passwordManager) {
			if (passwordManager.isInvalid(signupRequestDto.getPassword())) {
				throw new GeneralException(Status.MEMBER_PASSWORD_INVALID);
			}
		}
	},

	ALREADY_EXISTS_USERNAME {
		@Override
		public void validate(SignupRequestDto signupRequestDto, MemberService memberService, PasswordManager passwordManager){
            if(memberService.checkExistencesByUsername(signupRequestDto.getUsername())){
                throw new GeneralException(Status.ALREADY_EXISTS_USERNAME);
            }
        }
	},

    ALREADY_EXISTS_NICKNAME {
        @Override
        public void validate(SignupRequestDto signupRequestDto, MemberService memberService, PasswordManager passwordManager){
            if(memberService.checkExistencesByNickName(signupRequestDto.getNickname())){
                throw new GeneralException(Status.ALREADY_EXISTS_NICKNAME);
            }
        }
    };
    ;

	abstract void validate(SignupRequestDto memberRequestDto, MemberService memberService, PasswordManager passwordManager);

	public static void validateAll(SignupRequestDto signupRequestDto, MemberService memberService, PasswordManager passwordManager) {
		for (SignupValidationStatus status : SignupValidationStatus.values()) {
			status.validate(signupRequestDto, memberService,  passwordManager);
		}
	}
}