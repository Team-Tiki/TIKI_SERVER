package com.tiki.server.memberteammanager.service;

import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.memberteammanager.dto.response.BelongTeamsResponse;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberTeamManagerService {

    MemberTeamManagerFinder memberTeamManagerFinder;

    public BelongTeamsResponse showBelongTeam(long memberId){
        val belongTeams = memberTeamManagerFinder.findBelongTeamById;
    }
}
