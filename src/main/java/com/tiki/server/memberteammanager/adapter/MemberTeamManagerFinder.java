package com.tiki.server.memberteammanager.adapter;

import static com.tiki.server.memberteammanager.message.ErrorCode.INVALID_MEMBER_TEAM_MANAGER;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.memberteammanager.dto.response.BelongTeamsResponse;
import com.tiki.server.memberteammanager.entity.MemberTeamManager;
import com.tiki.server.memberteammanager.exception.MemberTeamManagerException;
import com.tiki.server.memberteammanager.repository.MemberTeamManagerRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberTeamManagerFinder {

    private final MemberTeamManagerRepository teamManagerRepository;

    public MemberTeamManager findByMemberIdAndTeamId(long memberId, long teamId) {
        return teamManagerRepository.findByMemberIdAndTeamId(memberId, teamId)
                .orElseThrow(() -> new MemberTeamManagerException(INVALID_MEMBER_TEAM_MANAGER));
    }
}
