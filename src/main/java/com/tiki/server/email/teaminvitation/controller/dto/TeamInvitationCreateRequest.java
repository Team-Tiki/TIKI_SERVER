package com.tiki.server.email.teaminvitation.controller.dto;

import jakarta.validation.constraints.NotNull;

public record TeamInvitationCreateRequest(
        @NotNull String email
) {
}
