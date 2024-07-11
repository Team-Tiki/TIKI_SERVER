package com.tiki.server.member.adapter;

import static com.tiki.server.member.message.ErrorCode.INVALID_MEMBER;
import static com.tiki.server.team.message.ErrorCode.INVALID_TEAM;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.member.entity.Member;
import com.tiki.server.member.exception.MemberException;
import com.tiki.server.member.repository.MemberRepository;
import com.tiki.server.team.entity.Team;
import com.tiki.server.team.exception.TeamException;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.tiki.server.member.message.ErrorCode.INVALID_MEMBER;

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
