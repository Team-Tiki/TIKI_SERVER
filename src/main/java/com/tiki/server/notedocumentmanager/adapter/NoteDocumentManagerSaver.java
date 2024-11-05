package com.tiki.server.notedocumentmanager.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.notedocumentmanager.entity.NoteDocumentManager;
import com.tiki.server.notedocumentmanager.repository.NoteDocumentRepository;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class NoteDocumentManagerSaver {

    private NoteDocumentRepository noteDocumentRepository;

    public NoteDocumentManager save(final NoteDocumentManager noteDocumentManager) {
        return noteDocumentRepository.save(noteDocumentManager);
    }
}
