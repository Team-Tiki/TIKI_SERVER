package com.tiki.server.member.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record BelongTeamsGetResponse(
        List<BelongTeamGetResponse> belongTeamGetResponses
) {
    public static BelongTeamsGetResponse from(List<BelongTeamGetResponse> belongTeamGetResponses) {
        return BelongTeamsGetResponse.builder().belongTeamGetResponses(belongTeamGetResponses).build();
    }
}
