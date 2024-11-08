package com.tiki.server.notedocumentmanager.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.notedocumentmanager.entity.NoteDocumentManager;
import com.tiki.server.notedocumentmanager.repository.NoteDocumentManagerRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RepositoryAdapter
@RequiredArgsConstructor
public class NoteDocumentManagerFinder {

    private final NoteDocumentManagerRepository noteDocumentManagerRepository;

    public List<NoteDocumentManager> findAllByNoteId(final long noteId){
        return noteDocumentManagerRepository.findAllByNoteId(noteId);

    }
}