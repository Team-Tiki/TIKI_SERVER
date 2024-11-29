package com.tiki.server.memberteammanager.service;

import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.memberteammanager.entity.MemberTeamManager;
import com.tiki.server.memberteammanager.service.dto.response.MemberTeamPositionGetResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberTeamManagerService {

    private final MemberTeamManagerFinder memberTeamManagerFinder;

    public MemberTeamPositionGetResponse getPosition(final long memberId, final long teamId) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamIdOrElseThrow(memberId, teamId);
        return MemberTeamPositionGetResponse.from(memberTeamManager.getPosition());
    }

    @Transactional
    public void updateTeamMemberName(final long memberId, final long teamId, final String name) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamIdOrElseThrow(memberId, teamId);
        memberTeamManager.setName(name);
    }
}
