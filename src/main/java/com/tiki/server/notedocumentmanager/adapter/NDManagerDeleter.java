package com.tiki.server.notedocumentmanager.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.notedocumentmanager.repository.NDManagerRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RepositoryAdapter
@RequiredArgsConstructor
public class NDManagerDeleter {

    private final NDManagerRepository ndManagerRepository;

    public void deleteByNoteIds(final List<Long> noteIds) {
        noteIds.forEach(ndManagerRepository::deleteAllByNoteId);
    }

    public void deleteByNoteIdAndDocumentId(final long noteId, final List<Long> documentIds) {
        documentIds.forEach(documentId ->
                ndManagerRepository.deleteByNoteIdAndDocumentId(noteId, documentId)
        );
    }
}
