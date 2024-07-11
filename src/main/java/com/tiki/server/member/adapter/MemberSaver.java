package com.tiki.server.member.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.member.entity.Member;
import com.tiki.server.member.exception.MemberException;
import com.tiki.server.member.repository.MemberRepository;
import com.tiki.server.timeblock.entity.TimeBlock;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.tiki.server.member.message.ErrorCode.INVALID_MEMBER;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberSaver {

    private final MemberRepository memberRepository;

    public void save(Member member) {
        memberRepository.save(member);
    }
}
