package com.tiki.server.memberteammanager.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.memberteammanager.repository.MemberTeamManagerRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberTeamManagerFinder {

	private final MemberTeamManagerRepository teamManagerRepository;
}
