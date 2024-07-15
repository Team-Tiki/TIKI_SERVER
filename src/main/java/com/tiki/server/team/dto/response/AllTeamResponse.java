package com.tiki.server.team.dto.response;

import com.tiki.server.team.vo.TeamVO;
import lombok.Builder;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record AllTeamResponse(
        List<TeamGetResponse> teams
) {
    public static AllTeamResponse from(List<TeamVO> teams) {
        return AllTeamResponse.builder()
                .teams(teams.stream().map(TeamGetResponse::from).toList())
                .build();
    }
}
