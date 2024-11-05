package com.tiki.server.note.service;

import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.note.adapter.NoteSaver;
import com.tiki.server.note.entity.Note;
import com.tiki.server.note.entity.NoteType;
import com.tiki.server.note.service.dto.request.NoteBaseDTO;
import com.tiki.server.note.service.dto.request.NoteFreeCreateDTO;
import com.tiki.server.note.service.dto.request.NoteTemplateCreateDTO;
import com.tiki.server.note.service.dto.response.NoteCreateResponseDTO;
import com.tiki.server.notedocumentmanager.adapter.NoteDocumentManagerSaver;
import com.tiki.server.notedocumentmanager.entity.NoteDocumentManager;
import com.tiki.server.notetimeblockmanager.adapter.NoteTimeBlockManagerSaver;
import com.tiki.server.notetimeblockmanager.entity.NoteTimeBlockManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final MemberTeamManagerFinder memberTeamManagerFinder;
    private final NoteSaver noteSaver;
    private final NoteTimeBlockManagerSaver noteTimeBlockManagerSaver;
    private final NoteDocumentManagerSaver noteDocumentManagerSaver;

    @Transactional
    public NoteCreateResponseDTO createNoteFree(final NoteFreeCreateDTO request) {
        memberTeamManagerFinder.findByMemberIdAndTeamId(request.memberId(), request.teamId());
        String encryptedContents = encryptedContents(request);
        Note note = createNote(NoteBaseDTO.of(request), encryptedContents, NoteType.FREE);
        return NoteCreateResponseDTO.from(note.getId());
    }

    @Transactional
    public NoteCreateResponseDTO createNoteTemplate(final NoteTemplateCreateDTO request) {
        memberTeamManagerFinder.findByMemberIdAndTeamId(request.memberId(), request.teamId());
        String encryptedContents = encryptedContents(request);
        Note note = createNote(NoteBaseDTO.of(request), encryptedContents, NoteType.TEMPLATE);
        return NoteCreateResponseDTO.from(note.getId());
    }

    private String encryptedContents(final NoteTemplateCreateDTO request) {
        String activity = Base64.getEncoder().encodeToString(request.answerWhatActivity().getBytes());
        String prepare = Base64.getEncoder().encodeToString(request.answerHowToPrepare().getBytes());
        String disappointing = Base64.getEncoder().encodeToString(request.answerWhatIsDisappointedThing().getBytes());
        String complement = Base64.getEncoder().encodeToString(request.answerHowToFix().getBytes());
        return String.join("|", activity, prepare, disappointing, complement);
    }

    private String encryptedContents(final NoteFreeCreateDTO request) {
        return Base64.getEncoder().encodeToString(request.contents().getBytes());
    }

    private Note createNote(final NoteBaseDTO request, final String encryptedContents, final NoteType noteType) {
        Note note = noteSaver.createNote(
                Note.of(
                        request.title(),
                        request.complete(),
                        request.startDate(),
                        request.endDate(),
                        encryptedContents,
                        request.memberId(),
                        request.teamId(),
                        noteType
                ));
        createNoteTimeBlockManagers(request.timeBlockIds(), note.getId());
        createNoteDocumentManagers(request.documentIds(), note.getId());
        return note;
    }

    private void createNoteTimeBlockManagers(final List<Long> timeBlockIds, final long noteId) {
        timeBlockIds.stream()
                .map(timeBlockId -> NoteTimeBlockManager.of(noteId, timeBlockId))
                .forEach(noteTimeBlockManagerSaver::save);
    }

    private void createNoteDocumentManagers(final List<Long> documentIds, final long noteId) {
        documentIds.stream()
                .map(documentId -> NoteDocumentManager.of(noteId, documentId))
                .forEach(noteDocumentManagerSaver::save);
    }
}