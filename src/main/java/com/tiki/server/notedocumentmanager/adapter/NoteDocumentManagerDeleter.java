package com.tiki.server.notedocumentmanager.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.notedocumentmanager.repository.NoteDocumentManagerRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RepositoryAdapter
@RequiredArgsConstructor
public class NoteDocumentManagerDeleter {

    private final NoteDocumentManagerRepository noteDocumentManagerRepository;

    public void noteDocumentMangerDeleteByNoteId(final long noteId) {
        noteDocumentManagerRepository.deleteAllByNoteId(noteId);
    }

    public void noteDocumentManagerDeleteByNoteIds(final List<Long> noteIds) {
        noteIds.forEach(noteDocumentManagerRepository::deleteAllByNoteId);
    }
}
