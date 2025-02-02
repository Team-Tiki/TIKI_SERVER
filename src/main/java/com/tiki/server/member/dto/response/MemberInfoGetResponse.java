package com.tiki.server.member.dto.response;

import java.time.LocalDate;

public record MemberInfoGetResponse(
        String Email,
        String name,
        LocalDate birth,
        String University
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
