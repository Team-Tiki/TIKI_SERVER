package com.tiki.server.memberteammanager.adapter;

import java.util.List;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.memberteammanager.entity.MemberTeamManager;
import com.tiki.server.memberteammanager.repository.MemberTeamManagerRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberTeamManagerDeleter {

	private final MemberTeamManagerRepository memberTeamManagerRepository;

	public void delete(final MemberTeamManager memberTeamManager) {
		memberTeamManagerRepository.delete(memberTeamManager);
	}

	public void deleteAll(final List<MemberTeamManager> memberTeamManagers) {
		memberTeamManagerRepository.deleteAll(memberTeamManagers);
	}
}
