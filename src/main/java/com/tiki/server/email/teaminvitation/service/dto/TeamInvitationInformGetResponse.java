package com.tiki.server.email.teaminvitation.service.dto;

import com.tiki.server.email.teaminvitation.entity.TeamInvitation;
import com.tiki.server.team.entity.Team;

public record TeamInvitationInformGetResponse(
        String sender,
        String teamName,
        String teamIconUrl,
        long teamId
) {

    public static TeamInvitationInformGetResponse of(
            final TeamInvitation invitation,
            final Team team
    ) {
        return new TeamInvitationInformGetResponse(
                invitation.getSender(),
                team.getName(),
                team.getIconImageUrl(),
                team.getId()
        );
    }
}
