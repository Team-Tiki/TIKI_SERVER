package com.tiki.server.notedocumentmanager.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.notedocumentmanager.repository.NDRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RepositoryAdapter
@RequiredArgsConstructor
public class NDDeleter {

    private final NDRepository ndRepository;

    public void deleteByNoteIds(final List<Long> noteIds) {
        noteIds.forEach(ndRepository::deleteAllByNoteId);
    }

    public void deleteByNoteIdAndDocumentId(final long noteId, final List<Long> documentIds) {
        documentIds.forEach(documentId ->
                ndRepository.deleteByNoteIdAndDocumentId(noteId, documentId)
        );
    }

    public void deleteAllByDocuments(final List<Long> documentIds) {
        ndRepository.deleteAllByDocumentIdIn(documentIds);
    }
}
