package com.tiki.server.notetimeblockmanager.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.notedocumentmanager.entity.NoteDocumentManager;
import com.tiki.server.notedocumentmanager.repository.NoteDocumentManagerRepository;
import com.tiki.server.notetimeblockmanager.entity.NoteTimeBlockManager;
import com.tiki.server.notetimeblockmanager.repository.NoteTimeBlockManagerRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RepositoryAdapter
@RequiredArgsConstructor
public class NoteTimeBlockManagerFinder {

    private final NoteTimeBlockManagerRepository noteTimeBlockManagerRepository;

    public List<NoteTimeBlockManager> findAllByNoteId(final long noteId){
        return noteTimeBlockManagerRepository.findAllByNoteId(noteId);

    }
}
