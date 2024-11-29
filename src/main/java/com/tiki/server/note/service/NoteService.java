package com.tiki.server.note.service;

import com.tiki.server.common.entity.SortOrder;
import com.tiki.server.common.util.ContentEncoder;
import com.tiki.server.document.adapter.DocumentFinder;
import com.tiki.server.document.entity.Document;
import com.tiki.server.member.adapter.MemberFinder;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.note.adapter.NoteDeleter;
import com.tiki.server.note.adapter.NoteFinder;
import com.tiki.server.note.adapter.NoteSaver;
import com.tiki.server.note.entity.Note;
import com.tiki.server.note.entity.NoteType;
import com.tiki.server.note.service.dto.request.*;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.tiki.server.common.Constants.INIT_NUM;
import static com.tiki.server.note.constants.NoteConstants.PAGE_SIZE;

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
    private final MemberFinder memberFinder;

    @Transactional
    public NoteCreateServiceResponse createNoteFree(final NoteFreeCreateServiceRequest request) {
        memberTeamManagerFinder.findByMemberIdAndTeamId(request.memberId(), request.teamId());
        String encryptedContents = ContentEncoder.encodeNoteFree(request.contents());
        Note note = createNote(NoteBase.of(request), encryptedContents, NoteType.FREE);
        createNoteTimeBlockManagers(request.timeBlockIds(), note.getId());
        createNoteDocumentManagers(request.documentIds(), note.getId());
        return NoteCreateServiceResponse.from(note.getId());
    }

    @Transactional
    public NoteCreateServiceResponse createNoteTemplate(final NoteTemplateCreateServiceRequest request) {
        memberTeamManagerFinder.findByMemberIdAndTeamId(request.memberId(), request.teamId());
        String encryptedContents = ContentEncoder.encodeNoteTemplate(
                request.answerWhatActivity(),
                request.answerHowToPrepare(),
                request.answerWhatIsDisappointedThing(),
                request.answerHowToFix()
        );
        Note note = createNote(NoteBase.of(request), encryptedContents, NoteType.TEMPLATE);
        createNoteTimeBlockManagers(request.timeBlockIds(), note.getId());
        createNoteDocumentManagers(request.documentIds(), note.getId());
        return NoteCreateServiceResponse.from(note.getId());
    }

    @Transactional
    public void updateNoteFree(final NoteFreeUpdateServiceRequest request) {
        Note note = noteFinder.findById(request.noteId());
        memberTeamManagerFinder.findByMemberIdAndTeamId(request.memberId(), request.teamId());
        String encryptedContents = ContentEncoder.encodeNoteFree(request.contents());
        note.updateValue(
                request.memberId(),
                request.teamId(),
                request.title(),
                encryptedContents,
                request.startDate(),
                request.endDate(),
                request.complete(),
                NoteType.FREE
        );
        updateNoteTimeBlockManager(request.timeBlockIds(), note.getId());
        updateNoteDocumentManager(request.documentIds(), note.getId());
    }

    @Transactional
    public void updateNoteTemplate(final NoteTemplateUpdateServiceRequest request) {
        Note note = noteFinder.findById(request.noteId());
        memberTeamManagerFinder.findByMemberIdAndTeamId(request.memberId(), request.teamId());
        String encryptedContents = ContentEncoder.encodeNoteTemplate(
                request.answerWhatActivity(),
                request.answerHowToPrepare(),
                request.answerWhatIsDisappointedThing(),
                request.answerHowToFix()
        );
        note.updateValue(
                request.memberId(),
                request.teamId(),
                request.title(),
                encryptedContents,
                request.startDate(),
                request.endDate(),
                request.complete(),
                NoteType.TEMPLATE
        );
        updateNoteTimeBlockManager(request.timeBlockIds(), request.noteId());
        updateNoteDocumentManager(request.documentIds(), request.noteId());
    }

    @Transactional
    public void deleteNotes(final List<Long> noteIds, final long teamId, final long memberId) {
        memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        noteDocumentManagerDeleter.deleteByNoteIds(noteIds);
        noteTimeBlockManagerDeleter.noteTimeBlockManagerDeleteByIds(noteIds);
        noteDeleter.deleteNoteByIds(noteIds);
    }

    public NoteListGetServiceResponse getNote(
            final long teamId,
            final long memberId,
            final LocalDateTime createdAt,
            final SortOrder sortOrder
    ) {
        memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        PageRequest pageable = PageRequest.of(INIT_NUM, PAGE_SIZE);
        List<Note> noteList = getNotes(createdAt, sortOrder, pageable, teamId);
        List<NoteGetResponse> noteGetResponses = noteList.stream()
                .map(note -> NoteGetResponse.of(note, getMemberName(note.getMemberId(), teamId)))
                .toList();
        return new NoteListGetServiceResponse(noteGetResponses);
    }

    public NoteDetailGetServiceResponse getNoteDetail(final long teamId, final long memberId, final long noteId) {
        memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        Note note = noteFinder.findById(noteId);
        List<Document> documentList = getDocumentListMappedByNote(noteId);
        List<TimeBlock> timeBlockList = getTimeBlocksMappedByNote(noteId);
        String memberName = getMemberName(note.getMemberId(), teamId);
        return note.getNoteType() == NoteType.FREE
                ? NoteFreeDetailGetServiceResponse.of(note, memberName, documentList, timeBlockList)
                : NoteTemplateDetailGetServiceResponse.of(note, memberName, documentList, timeBlockList);
    }

    private String getMemberName(final Long noteMemberId, final long teamId) {
        return Optional.ofNullable(noteMemberId)
                .map(id -> memberTeamManagerFinder.findByMemberIdAndTeamId(id, teamId).getName())
                .orElse("알 수 없음");
    }

    private void updateNoteDocumentManager(final List<Long> documentIds, final long noteId) {
        List<Long> existingNoteDocumentIds = noteDocumentManagerFinder.findAllByNoteId(noteId).stream()
                .map(NoteDocumentManager::getDocumentId)
                .toList();
        List<Long> idsToAdd = documentIds.stream()
                .filter(id -> !existingNoteDocumentIds.contains(id))
                .toList();
        List<Long> idsToRemove = existingNoteDocumentIds.stream()
                .filter(id -> !documentIds.contains(id))
                .toList();
        createNoteDocumentManagers(idsToAdd, noteId);
        noteDocumentManagerDeleter.deleteByNoteIdAndDocumentId(noteId, idsToRemove);
    }

    private void updateNoteTimeBlockManager(final List<Long> timeBlockIds, final long noteId) {
        List<Long> existingNoteTimeBlockIds = noteTimeBlockManagerFinder.findAllByNoteId(noteId).stream()
                .map(NoteTimeBlockManager::getTimeBlockId)
                .toList();
        List<Long> idsToAdd = timeBlockIds.stream()
                .filter(id -> !existingNoteTimeBlockIds.contains(id))
                .toList();
        List<Long> idsToRemove = existingNoteTimeBlockIds.stream()
                .filter(id -> !timeBlockIds.contains(id))
                .toList();
        createNoteTimeBlockManagers(idsToAdd, noteId);
        noteTimeBlockManagerDeleter.deleteByNoteIdAndTimeBlockId(noteId, idsToRemove);
    }

    private List<Note> getNotes(final LocalDateTime createdAt, final SortOrder sortOrder, final PageRequest pageable, final long teamId) {
        if (sortOrder == SortOrder.DESC) {
            return noteFinder.findByCreatedAtBeforeOrderByModifiedAtDesc(createdAt, pageable, teamId);
        }
        return noteFinder.findByCreatedAtAfterOrderByModifiedAtAsc(createdAt, pageable, teamId);
    }

    private List<TimeBlock> getTimeBlocksMappedByNote(final long noteId) {
        List<Long> timblockIdList = noteTimeBlockManagerFinder.findAllByNoteId(noteId).stream()
                .map(NoteTimeBlockManager::getTimeBlockId)
                .toList();
        return timblockIdList.stream()
                .map(timeBlockFinder::findByIdOrElseThrow)
                .toList();
    }

    private List<Document> getDocumentListMappedByNote(final long noteId) {
        List<Long> documentIdList = noteDocumentManagerFinder.findAllByNoteId(noteId).stream()
                .map(NoteDocumentManager::getDocumentId)
                .toList();
        return documentIdList.stream()
                .map(documentFinder::findByIdOrElseThrow)
                .toList();
    }

    private Note createNote(final NoteBase request, final String encryptedContents, final NoteType noteType) {
        return noteSaver.createNote(
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
    }

    private void createNoteTimeBlockManagers(final List<Long> timeBlockIds, final long noteId) {
        timeBlockIds.stream()
                .filter(timeBlockFinder::existsById)
                .map(timeBlockId -> NoteTimeBlockManager.of(noteId, timeBlockId))
                .forEach(noteTimeBlockManagerSaver::save);
    }

    private void createNoteDocumentManagers(final List<Long> documentIds, final long noteId) {
        documentIds.stream()
                .filter(documentFinder::existsById)
                .map(documentId -> NoteDocumentManager.of(noteId, documentId))
                .forEach(noteDocumentManagerSaver::save);
    }
}