package com.tiki.server.member.dto.request;

import com.tiki.server.common.entity.School;
import com.tiki.server.common.entity.University;
import lombok.Data;
import lombok.NonNull;

public record MemberProfileCreateRequest(
        @NonNull String name,
        @NonNull Data birth,
        @NonNull University Univ,
        @NonNull String email,
        @NonNull String password,
        @NonNull String passwordCk
        ) {
}
