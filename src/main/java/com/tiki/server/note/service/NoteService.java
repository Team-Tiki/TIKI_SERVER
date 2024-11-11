package com.tiki.server.note.service;

import com.tiki.server.common.util.ContentEncoder;
import com.tiki.server.document.adapter.DocumentFinder;
import com.tiki.server.document.entity.Document;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.memberteammanager.entity.MemberTeamManager;
import com.tiki.server.note.adapter.NoteDeleter;
import com.tiki.server.note.adapter.NoteFinder;
import com.tiki.server.note.adapter.NoteSaver;
import com.tiki.server.note.entity.Note;
import com.tiki.server.note.entity.NoteType;
import com.tiki.server.note.service.dto.request.NoteBaseDTO;
import com.tiki.server.note.service.dto.request.NoteDeleteDTO;
import com.tiki.server.note.service.dto.request.NoteFreeCreateDTO;
import com.tiki.server.note.service.dto.request.NoteTemplateCreateDTO;
import com.tiki.server.note.service.dto.response.*;
import com.tiki.server.notedocumentmanager.adapter.NoteDocumentManagerDeleter;
import com.tiki.server.notedocumentmanager.adapter.NoteDocumentManagerFinder;
import com.tiki.server.notedocumentmanager.adapter.NoteDocumentManagerSaver;
import com.tiki.server.notedocumentmanager.entity.NoteDocumentManager;
import com.tiki.server.notetimeblockmanager.adapter.NoteTimeBlockManagerDeleter;
import com.tiki.server.notetimeblockmanager.adapter.NoteTimeBlockManagerFinder;
import com.tiki.server.notetimeblockmanager.adapter.NoteTimeBlockManagerSaver;
import com.tiki.server.notetimeblockmanager.entity.NoteTimeBlockManager;
import com.tiki.server.timeblock.adapter.TimeBlockFinder;
import com.tiki.server.timeblock.entity.TimeBlock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final MemberTeamManagerFinder memberTeamManagerFinder;
    private final NoteSaver noteSaver;
    private final NoteFinder noteFinder;
    private final NoteDeleter noteDeleter;
    private final NoteTimeBlockManagerFinder noteTimeBlockManagerFinder;
    private final NoteTimeBlockManagerSaver noteTimeBlockManagerSaver;
    private final NoteTimeBlockManagerDeleter noteTimeBlockManagerDeleter;
    private final NoteDocumentManagerFinder noteDocumentManagerFinder;
    private final NoteDocumentManagerSaver noteDocumentManagerSaver;
    private final NoteDocumentManagerDeleter noteDocumentManagerDeleter;
    private final TimeBlockFinder timeBlockFinder;
    private final DocumentFinder documentFinder;

    @Transactional
    public NoteCreateResponseDTO createNoteFree(final NoteFreeCreateDTO request) {
        String author = memberTeamManagerFinder.findByMemberIdAndTeamId(request.memberId(), request.teamId()).getName();
        String encryptedContents = ContentEncoder.encodeNoteFree(request.contents());
        Note note = createNote(NoteBaseDTO.of(request), author, encryptedContents, NoteType.FREE);
        return NoteCreateResponseDTO.from(note.getId());
    }

    @Transactional
    public NoteCreateResponseDTO createNoteTemplate(final NoteTemplateCreateDTO request) {
        String author = memberTeamManagerFinder.findByMemberIdAndTeamId(request.memberId(), request.teamId()).getName();
        String encryptedContents = ContentEncoder.encodeNoteTemplate(
                request.answerWhatActivity(),
                request.answerHowToPrepare(),
                request.answerWhatIsDisappointedThing(),
                request.answerHowToFix()
        );
        Note note = createNote(NoteBaseDTO.of(request), author, encryptedContents, NoteType.TEMPLATE);
        return NoteCreateResponseDTO.from(note.getId());
    }

    @Transactional
    public void deleteNotes(final NoteDeleteDTO request) {
        memberTeamManagerFinder.findByMemberIdAndTeamId(request.memberId(), request.teamId());
        noteDocumentManagerDeleter.noteDeleteByIds(request.notesIds());
        noteTimeBlockManagerDeleter.noteDeleteByIds(request.notesIds());
        noteDeleter.deleteNoteByIds(request.notesIds());
    }

    public NoteGetListResponseDTO getNote(final long teamId, final long memberId) {
        memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        List<Note> noteList = noteFinder.findAllByTeamId(teamId);
        List<NoteGetResponseDTO> noteGetResponseDTOList = noteList.stream()
                .map(note -> {
                    MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(note.getMemberId(), teamId);
                    return NoteGetResponseDTO.of(note, memberTeamManager.getName());
                })
                .collect(Collectors.toList());
        return new NoteGetListResponseDTO(noteGetResponseDTOList);

    }

    public NoteGetDetailViewDTO getNoteDetail(final long teamId, final long memberId, final long noteId) {
        memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        Note note = noteFinder.findById(noteId);
        List<Document> documentList = getDocumentListMappedByNote(noteId);
        List<TimeBlock> timeBlockList = getTimeBlocksMappedByNote(noteId);
        return note.getNoteType() == NoteType.FREE ?
                NoteGetDetailFreeResponseDTO.of(note, documentList, timeBlockList):
                NoteGetDetailTemplateResponseDTO.of(note,documentList,timeBlockList);
    }

    private List<TimeBlock> getTimeBlocksMappedByNote(long noteId) {
        List<Long> timblockIdList = noteTimeBlockManagerFinder.findAllByNoteId(noteId).stream()
                .map(NoteTimeBlockManager::getTimeBlockId)
                .toList();
        return timblockIdList.stream()
                .map(timeBlockFinder::findByIdOrElseThrow)
                .toList();
    }

    private List<Document> getDocumentListMappedByNote(long noteId) {
        List<Long> documentIdList = noteDocumentManagerFinder.findAllByNoteId(noteId).stream()
                .map(NoteDocumentManager::getDocumentId)
                .toList();
        return documentIdList.stream()
                .map(documentFinder::findByIdOrElseThrow)
                .toList();
    }

    private Note createNote(final NoteBaseDTO request, final String author, final String encryptedContents, final NoteType noteType) {
        Note note = noteSaver.createNote(
                Note.of(
                        request.title(),
                        author,
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