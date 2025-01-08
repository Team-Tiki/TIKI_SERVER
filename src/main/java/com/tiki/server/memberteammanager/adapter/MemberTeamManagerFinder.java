package com.tiki.server.memberteammanager.adapter;

import static com.tiki.server.memberteammanager.message.ErrorCode.INVALID_MEMBER_TEAM_MANAGER;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.email.Email;
import com.tiki.server.memberteammanager.entity.MemberTeamManager;
import com.tiki.server.memberteammanager.exception.MemberTeamManagerException;
import com.tiki.server.memberteammanager.repository.MemberTeamManagerRepository;

import com.tiki.server.memberteammanager.repository.projection.TeamMemberInformGetProjection;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberTeamManagerFinder {

    private final MemberTeamManagerRepository memberTeamManagerRepository;

    public MemberTeamManager findByMemberIdAndTeamId(final long memberId, final long teamId) {
        return memberTeamManagerRepository.findByMemberIdAndTeamId(memberId, teamId)
                .orElseThrow(() -> new MemberTeamManagerException(INVALID_MEMBER_TEAM_MANAGER));
    }

    public List<MemberTeamManager> findAllByMemberIdOrderByCreatedAt(final long memberId) {
        return memberTeamManagerRepository.findAllByMemberIdOrderByCreatedAt(memberId);
    }

    public List<MemberTeamManager> findAllByTeamId(final long teamId) {
        return memberTeamManagerRepository.findAllByTeamId(teamId);
    }

    public List<TeamMemberInformGetProjection> findNameAndEmailByMemberIdAndTeamId(final long teamId) {
        return memberTeamManagerRepository.findTeamMembersByTeamId(teamId);
    }

    public boolean checkIsPresent(final long memberId, final long teamId) {
        return memberTeamManagerRepository.findByMemberIdAndTeamId(memberId, teamId).isPresent();
    }

    public boolean existsByTeamIdAndMemberEmail(final long teamId, final Email email){
        return memberTeamManagerRepository.existsByTeamIdAndMemberEmail(teamId,email);
    }
}
