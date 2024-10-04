package com.tiki.server.emailverification.dto.request;

import lombok.NonNull;

public record EmailRequest(
       @NonNull String email
) {
}