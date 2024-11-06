package com.tiki.server.note.service.dto.request;

import java.time.LocalDate;
import java.util.List;

public record NoteBaseDTO(
        String title,
        boolean complete,
        LocalDate startDate,
        LocalDate endDate,
        long memberId,
        long teamId,
        List<Long> timeBlockIds,
        List<Long> documentIds
) {

    public static NoteBaseDTO of(final NoteFreeCreateDTO noteFreeCreateDTO) {
        return new NoteBaseDTO(
                noteFreeCreateDTO.title(),
                noteFreeCreateDTO.complete(),
                noteFreeCreateDTO.startDate(),
                noteFreeCreateDTO.endDate(),
                noteFreeCreateDTO.memberId(),
                noteFreeCreateDTO.teamId(),
                noteFreeCreateDTO.timeBlockIds(),
                noteFreeCreateDTO.documentIds()
        );
    }

    public static NoteBaseDTO of(final NoteTemplateCreateDTO noteTemplateCreateDTO) {
        return new NoteBaseDTO(
                noteTemplateCreateDTO.title(),
                noteTemplateCreateDTO.complete(),
                noteTemplateCreateDTO.startDate(),
                noteTemplateCreateDTO.endDate(),
                noteTemplateCreateDTO.memberId(),
                noteTemplateCreateDTO.teamId(),
                noteTemplateCreateDTO.timeBlockIds(),
                noteTemplateCreateDTO.documentIds()
        );
    }
}
