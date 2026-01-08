package com.tiki.server.teaminvitation.service.dto;

import com.tiki.server.teaminvitation.entity.TeamInvitation;
import jakarta.validation.constraints.NotNull;

public record TeamInvitationEmailGetResponse(
        @NotNull String email
) {
    public static TeamInvitationEmailGetResponse from(final TeamInvitation teamInvitation) {
        return new TeamInvitationEmailGetResponse(teamInvitation.getEmailToString());
    }
}
