package com.tiki.server.memberteammanager.service;

import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.memberteammanager.dto.response.BelongTeamsResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberTeamManagerService {

    private final MemberTeamManagerFinder memberTeamManagerFinder;

    public List<BelongTeamsResponse> findBelongTeams(long memberId) {
        return memberTeamManagerFinder.findBelongTeamByMemberId(memberId).stream().map(BelongTeamsResponse::from).toList();
    }
}
