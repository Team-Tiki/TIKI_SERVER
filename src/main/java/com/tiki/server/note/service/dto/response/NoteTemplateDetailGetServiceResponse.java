package com.tiki.server.note.service.dto.response;

import com.tiki.server.common.util.ContentDecoder;
import com.tiki.server.document.entity.Document;
import com.tiki.server.document.service.dto.response.DocumentTagGetServiceResponse;
import com.tiki.server.note.entity.Note;
import com.tiki.server.note.entity.NoteType;
import com.tiki.server.timeblock.entity.TimeBlock;
import com.tiki.server.timeblock.service.dto.response.TimeBlockTagServiceResponse;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

public record NoteTemplateDetailGetServiceResponse(
        long noteId,
        @NonNull NoteType noteType,
        @NonNull String title,
        @NonNull String author,
        @NonNull LocalDate startDate,
        @NonNull LocalDate endDate,
        boolean complete,
        @NonNull String answerWhatActivity,
        @NonNull String answerHowToPrepare,
        @NonNull String answerWhatIsDisappointedThing,
        @NonNull String answerHowToFix,
        List<DocumentTagGetServiceResponse> documentList,
        List<TimeBlockTagServiceResponse> timeBlockList
) implements NoteDetailGetServiceResponse {

    public static NoteTemplateDetailGetServiceResponse of(
            final Note note,
            final String author,
            final List<Document> documentList,
            final List<TimeBlock> timeBlockList
    ) {
        List<String> contents = ContentDecoder.decodeNoteTemplate(note.getContents());
        return new NoteTemplateDetailGetServiceResponse(
                note.getId(),
                NoteType.TEMPLATE,
                note.getTitle(),
                author,
                note.getStartDate(),
                note.getEndDate(),
                note.isComplete(),
                contents.get(0),
                contents.get(1),
                contents.get(2),
                contents.get(3),
                documentList.stream().map(DocumentTagGetServiceResponse::from).toList(),
                timeBlockList.stream().map(TimeBlockTagServiceResponse::from).toList()
        );
    }
}
