package com.tiki.server.memberteammanager.adapter;

import static com.tiki.server.memberteammanager.message.ErrorCode.INVALID_MEMBER_TEAM_MANAGER;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.memberteammanager.entity.MemberTeamManager;
import com.tiki.server.memberteammanager.exception.MemberTeamManagerException;
import com.tiki.server.memberteammanager.repository.MemberTeamManagerRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberTeamManagerFinder {

    private final MemberTeamManagerRepository teamManagerRepository;

    public MemberTeamManager findByMemberIdAndTeamId(long memberId, long teamId) {
        return teamManagerRepository.findByMemberIdAndTeamId(memberId, teamId)
                .orElseThrow(() -> new MemberTeamManagerException(INVALID_MEMBER_TEAM_MANAGER));
    }

    public List<MemberTeamManager> findBelongTeamByMemberId(long memberId) {
        return teamManagerRepository.findAllBelongTeamByMemberId(memberId).stream().toList();
    }

    public List<MemberTeamManager> findAllByTeamId(long teamId) {
        return teamManagerRepository.findAllByTeamId(teamId);
    }
}
