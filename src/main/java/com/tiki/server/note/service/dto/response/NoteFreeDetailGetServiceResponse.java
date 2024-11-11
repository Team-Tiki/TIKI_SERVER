package com.tiki.server.note.service.dto.response;

import com.tiki.server.common.util.ContentDecoder;
import com.tiki.server.document.entity.Document;
import com.tiki.server.document.service.dto.response.DocumentTagServiceResponse;
import com.tiki.server.note.entity.Note;
import com.tiki.server.note.entity.NoteType;
import com.tiki.server.timeblock.entity.TimeBlock;
import com.tiki.server.timeblock.service.dto.response.TimeBlockTagServiceResponse;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

public record NoteFreeDetailGetServiceResponse(
        @NonNull NoteType noteType,
        @NonNull String title,
        @NonNull String author,
        @NonNull LocalDate startDate,
        @NonNull LocalDate endDate,
        boolean complete,
        @NonNull String contents,
        List<DocumentTagServiceResponse> documentList,
        List<TimeBlockTagServiceResponse> timeBlockList
) implements NoteDetailGetServiceResponse {

    public static NoteFreeDetailGetServiceResponse of(
            final Note note,
            final List<Document> documentList,
            final List<TimeBlock> timeBlockList
    ) {
        return new NoteFreeDetailGetServiceResponse(
                NoteType.FREE,
                note.getTitle(),
                note.getAuthor(),
                note.getStartDate(),
                note.getEndDate(),
                note.isComplete(),
                ContentDecoder.decodeNoteFree(note.getContents()),
                documentList.stream().map(DocumentTagServiceResponse::from).toList(),
                timeBlockList.stream().map(TimeBlockTagServiceResponse::from).toList()
        );
    }
}
