package com.tiki.server.email.teaminvitation.service.dto;

import com.tiki.server.email.Email;
import com.tiki.server.email.teaminvitation.entity.TeamInvitation;
import jakarta.validation.constraints.NotNull;

public record TeamInvitationEmailGetResponse(
        @NotNull String email
) {
    public static TeamInvitationEmailGetResponse from(final TeamInvitation teamInvitation) {
        return new TeamInvitationEmailGetResponse(teamInvitation.getEmailToString());
    }
}
