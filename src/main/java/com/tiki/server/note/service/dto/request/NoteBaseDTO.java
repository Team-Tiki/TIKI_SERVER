package com.tiki.server.note.service.dto.request;

import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

public record NoteBaseDTO(
        @NonNull String title,
        boolean complete,
        @NonNull LocalDate startDate,
        @NonNull LocalDate endDate,
        long memberId,
        long teamId,
        List<Long> timeBlockIds,
        List<Long> documentIds
) {

    public static NoteBaseDTO of(final NoteFreeCreateServiceRequest noteFreeCreateServiceRequest) {
        return new NoteBaseDTO(
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

    public static NoteBaseDTO of(final NoteTemplateCreateServiceRequest noteTemplateCreateServiceRequest) {
        return new NoteBaseDTO(
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
