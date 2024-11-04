package com.tiki.server.note.service.dto.request;

import com.tiki.server.note.entity.vo.TitleVo;
import lombok.NonNull;

import java.time.LocalDate;

public record NoteFreeCreateDTO(
        @NonNull TitleVo titleVo,
        boolean complete,
        @NonNull LocalDate startDate,
        @NonNull LocalDate endDate,
        @NonNull String contents,
        long teamId,
        long memberId
) {
    public static NoteFreeCreateDTO of(
            final TitleVo titleVo,
            final boolean complete,
            final LocalDate startDate,
            final LocalDate endDate,
            final String contents,
            final long teamId,
            final long memberId
    ) {
        return new NoteFreeCreateDTO(titleVo, complete, startDate, endDate, contents, teamId, memberId);
    }
}
