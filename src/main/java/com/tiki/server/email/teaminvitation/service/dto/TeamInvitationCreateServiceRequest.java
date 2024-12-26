package com.tiki.server.email.teaminvitation.service.dto;

import com.tiki.server.email.Email;
import jakarta.validation.constraints.NotNull;

public record TeamInvitationCreateServiceRequest(
        long teamId,
        long memberId,
        @NotNull Email email
) {
    public static TeamInvitationCreateServiceRequest of(final long teamId, final long memberId, final String email) {
        return new TeamInvitationCreateServiceRequest(teamId, memberId, Email.from(email));
    }
}
