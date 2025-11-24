package com.deview.server.domain.auth.service;


import com.deview.server.domain.auth.dto.request.LoginRequestDto;
import com.deview.server.domain.auth.dto.request.SignupRequestDto;
import com.deview.server.domain.auth.util.PasswordManager;
import com.deview.server.domain.member.domain.CustomUserDetails;
import com.deview.server.domain.member.domain.Member;
import com.deview.server.domain.member.dto.profile.response.MemberResponse;
import com.deview.server.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final PasswordManager passwordManager;

    // 회원가입
    public void signUp(SignupRequestDto signupRequestDto){
        SignupValidationStatus.validateAll(signupRequestDto,memberService,passwordManager);
        Member member = Member.toEntity(signupRequestDto, passwordEncoder);
        memberService.saveMember(member);
    }

    // 자체 로그인
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberService.getMemberByUsername(username);
        return new CustomUserDetails(member);
    }

    // 멤버 존재 여부
    @Transactional(readOnly = true)
    public Boolean isExistMember(LoginRequestDto dto){
        return memberService.checkExistencesByUsername(dto.getUsername());
    }

    public MemberResponse memberProfile(String token){
        return memberService.getMyInfo(token);
    }

}
