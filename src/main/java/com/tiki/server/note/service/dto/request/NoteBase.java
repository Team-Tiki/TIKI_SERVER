package com.tiki.server.note.service.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record NoteBase(
    @NotNull String title,
    @NotNull boolean complete,
    @NotNull LocalDate startDate,
    @NotNull  LocalDate endDate,
    @NotNull long memberId,
    @NotNull long teamId,
    @NotNull List<Long> timeBlockIds,
    @NotNull List<Long> documentIds
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
