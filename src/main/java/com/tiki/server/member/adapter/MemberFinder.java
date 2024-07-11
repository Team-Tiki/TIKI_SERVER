package com.tiki.server.member.adapter;

import static com.tiki.server.member.message.ErrorCode.INVALID_MEMBER;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.member.entity.Member;
import com.tiki.server.member.exception.MemberException;
import com.tiki.server.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberFinder {

    private final MemberRepository memberRepository;

    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Member findById(long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(INVALID_MEMBER));
    }
}
