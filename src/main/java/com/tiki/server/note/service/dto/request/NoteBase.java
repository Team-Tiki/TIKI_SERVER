package com.tiki.server.note.service.dto.request;

import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

public record NoteBase(
        @NonNull String title,
        boolean complete,
        @NonNull LocalDate startDate,
        @NonNull LocalDate endDate,
        long memberId,
        long teamId,
        List<Long> timeBlockIds,
        List<Long> documentIds
) {

    public static NoteBase of(final NoteFreeCreateServiceRequest noteFreeCreateServiceRequest) {
        return new NoteBase(
                noteFreeCreateServiceRequest.title(),
                noteFreeCreateServiceRequest.complete(),
                noteFreeCreateServiceRequest.startDate(),
                noteFreeCreateServiceRequest.endDate(),
                noteFreeCreateServiceRequest.memberId(),
                noteFreeCreateServiceRequest.teamId(),
                noteFreeCreateServiceRequest.timeBlockIds(),
                noteFreeCreateServiceRequest.documentIds()
        );
    }

    public static NoteBase of(final NoteTemplateCreateServiceRequest noteTemplateCreateServiceRequest) {
        return new NoteBase(
                noteTemplateCreateServiceRequest.title(),
                noteTemplateCreateServiceRequest.complete(),
                noteTemplateCreateServiceRequest.startDate(),
                noteTemplateCreateServiceRequest.endDate(),
                noteTemplateCreateServiceRequest.memberId(),
                noteTemplateCreateServiceRequest.teamId(),
                noteTemplateCreateServiceRequest.timeBlockIds(),
                noteTemplateCreateServiceRequest.documentIds()
        );
    }
}
