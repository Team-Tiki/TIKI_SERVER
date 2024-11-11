package com.tiki.server.note.service.dto.response;

import java.util.List;

public record NoteListGetServiceResponse(
        List<NoteGetResponseDTO> noteGetResponseDTOList
) {

    public static NoteListGetServiceResponse of(List<NoteGetResponseDTO> noteGetResponseDTOList) {
        return new NoteListGetServiceResponse(noteGetResponseDTOList);
    }
}