package com.tiki.server.member.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.member.entity.Member;
import com.tiki.server.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberDeleter {

	private final MemberRepository memberRepository;

	public void delete(final Member member) {
		memberRepository.delete(member);
	}
}
