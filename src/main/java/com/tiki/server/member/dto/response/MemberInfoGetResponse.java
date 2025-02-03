package com.tiki.server.member.dto.response;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record MemberInfoGetResponse(
       @NotNull String Email,
       @NotNull String name,
       @NotNull LocalDate birth,
       @NotNull String University
) {

    public static MemberInfoGetResponse from(
            final String email,
            final String name,
            final LocalDate birth,
            final String university
    ) {
        return new MemberInfoGetResponse(email, name,birth, university);
    }
}
