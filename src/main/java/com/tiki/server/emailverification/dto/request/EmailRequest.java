package com.tiki.server.emailverification.dto.request;

import jakarta.validation.constraints.NotNull;

public record EmailRequest(
       @NotNull String email
) {
}