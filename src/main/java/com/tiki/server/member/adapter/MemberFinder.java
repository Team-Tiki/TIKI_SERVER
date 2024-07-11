package com.tiki.server.member.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.member.entity.Member;
import com.tiki.server.member.exception.MemberException;
import com.tiki.server.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.tiki.server.member.message.ErrorCode.INVALID_MEMBER;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberFinder {

	private final MemberRepository memberRepository;

	public Optional<Member> findMemberByEmail(String email){
		return memberRepository.findByEmail(email);
	}

}
