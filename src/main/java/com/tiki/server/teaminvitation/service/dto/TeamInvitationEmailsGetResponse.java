package com.tiki.server.teaminvitation.service.dto;

import com.tiki.server.teaminvitation.entity.Invitation;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record TeamInvitationEmailsGetResponse(
        @NotNull List<TeamInvitationEmailGetResponse> teamInvitationEmailGetResponses
) {
    public static TeamInvitationEmailsGetResponse from(final List<Invitation> invitations) {
        return new TeamInvitationEmailsGetResponse(
                invitations.stream()
                        .map(TeamInvitationEmailGetResponse::from)
                        .toList());
    }
}
