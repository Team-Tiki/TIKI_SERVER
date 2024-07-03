package com.tiki.server.member.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberFinder {

	private final MemberRepository memberRepository;
}
