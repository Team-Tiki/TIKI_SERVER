package com.tiki.server.member.dto.request;

import lombok.Data;
import lombok.NonNull;

public record MemberProfileCreateRequest(
        @NonNull String name,
        @NonNull Data birth

        ) {
}
