package com.deview.server.domain.member.service;

import com.deview.server.domain.member.domain.Member;
import com.deview.server.domain.member.repository.MemberRepository;
import com.deview.server.global.response.GeneralException;
import com.deview.server.global.response.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;


    public void saveMember(Member member) {
        save(member);
    }

    public Member getMemberByUsername(String username) {
        return findMemberByUsername(username);
    }

    public Boolean checkExistencesByUsername(String username) {
        return existsByUsername(username);
    }

    public Boolean checkExistencesByNickName(String nickname) {
        return existsByNickName(nickname);
    }

    public void save(Member member) {
        memberRepository.save(member);
    }

    private Member findMemberByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new GeneralException(Status.MEMBER_NOT_FOUND));
    }



    private Boolean existsByUsername(String username) {
        return memberRepository.existsByUsername(username);
    }

    private Boolean existsByNickName(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

}
