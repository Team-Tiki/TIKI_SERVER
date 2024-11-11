package com.tiki.server.note.service.dto.response;

import com.tiki.server.common.util.ContentDecoder;
import com.tiki.server.document.entity.Document;
import com.tiki.server.document.service.dto.response.DocumentDownloadDTO;
import com.tiki.server.note.entity.Note;
import com.tiki.server.note.entity.NoteType;
import com.tiki.server.timeblock.entity.TimeBlock;
import com.tiki.server.timeblock.service.dto.response.TimeBlockNameDTO;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

public record NoteGetDetailTemplateResponseDTO(
        NoteType noteType,
        String title,
        String author,
        LocalDate startDate,
        LocalDate endDate,
        boolean complete,
        String answerWhatActivity,
        String answerHowToPrepare,
        String answerWhatIsDisappointedThing,
        String answerHowToFix,
        List<DocumentDownloadDTO> documentList,
        List<TimeBlockNameDTO> timeBlockList
) implements NoteGetDetailViewDTO {
    public static NoteGetDetailTemplateResponseDTO of(
            final Note note,
            final List<Document> documentList,
            final List<TimeBlock> timeBlockList
    ) {
        List<String> contents = ContentDecoder.decodeNoteTemplate(note.getContents());
        return new NoteGetDetailTemplateResponseDTO(
                NoteType.TEMPLATE,
                note.getTitle(),
                note.getAuthor(),
                note.getStartDate(),
                note.getEndDate(),
                note.isComplete(),
                contents.get(0),
                contents.get(1),
                contents.get(2),
                contents.get(3),
                documentList.stream().map(DocumentDownloadDTO::from).toList(),
                timeBlockList.stream().map(TimeBlockNameDTO::from).toList()
        );
    }
}
