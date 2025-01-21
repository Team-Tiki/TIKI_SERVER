package com.tiki.server.member.dto.request;

import java.time.LocalDate;

import com.tiki.server.common.entity.University;

import jakarta.validation.constraints.NotNull;

public record MemberProfileCreateRequest(
        @NotNull String name,
        @NotNull LocalDate birth,
        @NotNull University univ,
        @NotNull String email,
        @NotNull String password,
        @NotNull String passwordChecker
) {
}
