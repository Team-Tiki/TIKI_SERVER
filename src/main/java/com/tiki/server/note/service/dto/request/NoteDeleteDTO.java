package com.tiki.server.note.service.dto.request;

import java.util.List;

public record NoteDeleteDTO(
        List<Long> notesIds,
        long teamId,
        long memberId
) {

    public static NoteDeleteDTO of(List<Long> notesIds,long teamId,long memberId){
        return new NoteDeleteDTO(notesIds, teamId, memberId);
    }
}
