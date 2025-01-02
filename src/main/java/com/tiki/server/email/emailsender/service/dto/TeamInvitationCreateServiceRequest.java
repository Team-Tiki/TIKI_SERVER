package com.tiki.server.email.emailsender.service.dto;

import com.tiki.server.email.Email;
import jakarta.validation.constraints.NotNull;

public record TeamInvitationCreateServiceRequest(
        @NotNull Email targetEmail,
        @NotNull long teamId,
        @NotNull long senderId
        ) {

    public static TeamInvitationCreateServiceRequest of(final String targetEmail,final long teamId,final long senderId ){
        return new TeamInvitationCreateServiceRequest(Email.from(targetEmail), teamId, senderId);
    }
}
