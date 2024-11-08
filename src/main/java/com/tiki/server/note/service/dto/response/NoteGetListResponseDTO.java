package com.tiki.server.note.service.dto.response;

import java.util.List;

public record NoteGetListResponseDTO(
        List<NoteGetResponseDTO> noteGetResponseDTOList
) {
    public static NoteGetListResponseDTO of(List<NoteGetResponseDTO> noteGetResponseDTOList){
        return new NoteGetListResponseDTO(noteGetResponseDTOList);
    }
}
