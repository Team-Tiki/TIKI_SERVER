package com.tiki.server.notetimeblockmanager.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.notetimeblockmanager.entity.NoteTimeBlockManager;
import com.tiki.server.notetimeblockmanager.repository.NoteTimeBlockManagerRepository;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class NoteTimeBlockManagerSaver {

    private NoteTimeBlockManagerRepository noteTimeBlockManagerRepository;

    public NoteTimeBlockManager save(final NoteTimeBlockManager noteTimeBlockManager) {
        return noteTimeBlockManagerRepository.save(noteTimeBlockManager);
    }
}
