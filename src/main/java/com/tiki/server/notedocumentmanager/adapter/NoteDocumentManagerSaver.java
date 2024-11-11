package com.tiki.server.notedocumentmanager.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.notedocumentmanager.entity.NoteDocumentManager;
import com.tiki.server.notedocumentmanager.repository.NoteDocumentManagerRepository;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class NoteDocumentManagerSaver {

    private final NoteDocumentManagerRepository noteDocumentManagerRepository;

    public NoteDocumentManager save(final NoteDocumentManager noteDocumentManager) {
        return noteDocumentManagerRepository.save(noteDocumentManager);
    }
}
