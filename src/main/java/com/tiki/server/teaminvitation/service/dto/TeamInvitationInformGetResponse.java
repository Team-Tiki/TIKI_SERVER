package com.tiki.server.teaminvitation.service.dto;

import com.tiki.server.teaminvitation.entity.TeamInvitation;
import com.tiki.server.team.dto.response.TeamResponse;
import jakarta.validation.constraints.NotNull;

public record TeamInvitationInformGetResponse(
        @NotNull String sender,
        @NotNull String teamName,
        @NotNull String teamIconUrl,
        @NotNull long teamId
) {

    public static TeamInvitationInformGetResponse of(
            final TeamInvitation teamInvitation,
            final TeamResponse team
    ) {
        return new TeamInvitationInformGetResponse(
                teamInvitation.getSender(),
                team.name(),
                team.iconImageUrl(),
                team.teamId()
        );
    }
}
