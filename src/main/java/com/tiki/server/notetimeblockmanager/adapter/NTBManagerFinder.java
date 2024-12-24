package com.tiki.server.notetimeblockmanager.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.notetimeblockmanager.entity.NTBManager;
import com.tiki.server.notetimeblockmanager.repository.NTBManagerRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RepositoryAdapter
@RequiredArgsConstructor
public class NTBManagerFinder {

    private final NTBManagerRepository ntbManagerRepository;

    public List<NTBManager> findAllByNoteId(final long noteId) {
        return ntbManagerRepository.findAllByNoteId(noteId);
    }

    public List<NTBManager> findAllByTimeBlockId(final long timeBlockId) {
        return ntbManagerRepository.findAllByTimeBlockId(timeBlockId);
    }
}
