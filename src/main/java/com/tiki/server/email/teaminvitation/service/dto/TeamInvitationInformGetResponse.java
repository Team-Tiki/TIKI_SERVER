package com.tiki.server.email.teaminvitation.service.dto;

import com.tiki.server.email.teaminvitation.entity.TeamInvitation;
import com.tiki.server.team.dto.response.TeamResponse;
import jakarta.validation.constraints.NotNull;

public record TeamInvitationInformGetResponse(
        @NotNull String sender,
        @NotNull String teamName,
        @NotNull String teamIconUrl,
        @NotNull long teamId
) {

    public static TeamInvitationInformGetResponse of(
            final TeamInvitation invitation,
            final TeamResponse team
    ) {
        return new TeamInvitationInformGetResponse(
                invitation.getSender(),
                team.name(),
                team.iconImageUrl(),
                team.teamId()
        );
    }
}
