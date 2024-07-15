package com.tiki.server.team.dto.response;

import com.tiki.server.team.entity.Team;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record AllTeamResponse(
        List<TeamResponse> teamResponse
) {
    public static AllTeamResponse from(List<Team> teams){
        return AllTeamResponse.builder()
                .teamResponse(teams.stream().map(TeamResponse::from).toList())
                .build();
    }
}
