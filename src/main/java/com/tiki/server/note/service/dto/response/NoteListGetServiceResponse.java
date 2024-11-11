package com.tiki.server.note.service.dto.response;

import java.util.List;

public record NoteListGetServiceResponse(
        List<NoteGetResponse> noteGetResponseList
) {

    public static NoteListGetServiceResponse of(List<NoteGetResponse> noteGetResponseList) {
        return new NoteListGetServiceResponse(noteGetResponseList);
    }
}