package com.tiki.server.member.dto.request;

import com.tiki.server.common.entity.School;
import lombok.Data;
import lombok.NonNull;

public record MemberProfileCreateRequest(
        @NonNull String name,
        @NonNull Data birth,
        @NonNull School school,
        @NonNull String email,
        @NonNull String password,
        @NonNull String passwordCk
        ) {
}
