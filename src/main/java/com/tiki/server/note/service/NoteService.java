package com.tiki.server.note.service;

import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.note.adapter.NoteSaver;
import com.tiki.server.note.entity.Note;
import com.tiki.server.note.service.dto.request.NoteCreateDTO;
import com.tiki.server.note.service.dto.response.NoteCreateResponseDTO;
import com.tiki.server.notedocumentmanager.adapter.NoteDocumentManagerSaver;
import com.tiki.server.notedocumentmanager.entity.NoteDocumentManager;
import com.tiki.server.notetimeblockmanager.adapter.NoteTimeBlockManagerSaver;
import com.tiki.server.notetimeblockmanager.entity.NoteTimeBlockManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final MemberTeamManagerFinder memberTeamManagerFinder;
    private final NoteSaver noteSaver;
    private final NoteTimeBlockManagerSaver noteTimeBlockManagerSaver;
    private final NoteDocumentManagerSaver noteDocumentManagerSaver;

    @Transactional
    public NoteCreateResponseDTO createNoteFree(final NoteCreateDTO request) {
        memberTeamManagerFinder.findByMemberIdAndTeamId(request.memberId(), request.teamId());
        Note note = noteSaver.createNote(
                Note.of(
                        request.title(),
                        request.complete(),
                        request.startDate(),
                        request.endDate(),
                        request.contents(),
                        request.memberId(),
                        request.teamId()
                ));
        request.timeBlockIds().stream()
                .map(timeBlockId -> NoteTimeBlockManager.of(note.getId(), timeBlockId))
                .forEach(noteTimeBlockManagerSaver::save);
        request.documentIds().stream()
                .map(documentId -> NoteDocumentManager.of(note.getId(), documentId))
                .forEach(noteDocumentManagerSaver::save);
        return NoteCreateResponseDTO.from(note.getId());
    }
}