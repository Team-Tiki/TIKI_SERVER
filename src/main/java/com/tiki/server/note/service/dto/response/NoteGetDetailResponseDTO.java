package com.tiki.server.note.service.dto.response;

import com.tiki.server.common.util.ContentDecoder;
import com.tiki.server.document.entity.Document;
import com.tiki.server.document.service.dto.response.DocumentDownloadDTO;
import com.tiki.server.note.entity.Note;
import com.tiki.server.timeblock.entity.TimeBlock;
import com.tiki.server.timeblock.service.dto.response.TimeBlockNameDTO;

import java.time.LocalDate;
import java.util.List;

public record NoteGetDetailResponseDTO(
        String author,
        LocalDate startDate,
        LocalDate endDate,
        boolean complete,
        String contents,
        List<DocumentDownloadDTO> documentList,
        List<TimeBlockNameDTO> timeBlockList
) {
    public static NoteGetDetailResponseDTO of(
            final Note note,
            final List<Document> documentList,
            final List<TimeBlock> timeBlockList
    ) {
        return new NoteGetDetailResponseDTO(
                note.getAuthor(),
                note.getStartDate(),
                note.getEndDate(),
                note.isComplete(),
                ContentDecoder.encodeNoteFree(note.getContents()),
                documentList.stream().map(DocumentDownloadDTO::from).toList(),
                timeBlockList.stream().map(TimeBlockNameDTO::from).toList()
        );
    }
}
