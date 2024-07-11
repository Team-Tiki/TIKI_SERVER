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

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberFinder {

	private final MemberRepository memberRepository;

	public Member findById(long memberId) {
		return memberRepository.findById(memberId)
			.orElseThrow(() -> new MemberException(INVALID_MEMBER));
	}
}
