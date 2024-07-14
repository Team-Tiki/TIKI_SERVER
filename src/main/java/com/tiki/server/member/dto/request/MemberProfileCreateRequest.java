package com.tiki.server.member.dto.request;

import java.time.LocalDate;

import com.tiki.server.common.entity.University;
import lombok.NonNull;

public record MemberProfileCreateRequest(
        @NonNull String name,
        @NonNull LocalDate birth,
        @NonNull University univ,
        @NonNull String email,
        @NonNull String password,
        @NonNull String passwordChecker
) {
}
