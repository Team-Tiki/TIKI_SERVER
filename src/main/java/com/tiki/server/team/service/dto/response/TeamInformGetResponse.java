package com.tiki.server.team.service.dto.response;

import com.tiki.server.common.entity.University;

public record TeamInformGetResponse(
        String teamName,
        University university,
        String teamIconUrl
) {

    public static TeamInformGetResponse from(final String teamName, final University university, final String teamIconUrl) {
        return new TeamInformGetResponse(teamName, university, teamIconUrl);
    }
}
