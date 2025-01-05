package com.tiki.server.email.teaminvitation.service.dto;

import com.tiki.server.email.teaminvitation.entity.TeamInvitation;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record TeamInvitationEmailsGetResponse(
        @NotNull List<TeamInvitationEmailGetResponse> teamInvitationEmailGetResponses
) {
    public static TeamInvitationEmailsGetResponse from(final List<TeamInvitation> teamInvitations) {
        return new TeamInvitationEmailsGetResponse(
                teamInvitations.stream()
                        .map(TeamInvitationEmailGetResponse::from)
                        .toList());
    }
}
