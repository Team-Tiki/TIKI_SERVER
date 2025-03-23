package com.tiki.server.teaminvitation.service.dto;

import com.tiki.server.teaminvitation.entity.Invitation;
import jakarta.validation.constraints.NotNull;

public record TeamInvitationEmailGetResponse(
        @NotNull String email
) {
    public static TeamInvitationEmailGetResponse from(final Invitation invitation) {
        return new TeamInvitationEmailGetResponse(invitation.getEmailToString());
    }
}
