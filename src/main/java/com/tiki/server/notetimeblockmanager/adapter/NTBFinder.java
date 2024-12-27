package com.tiki.server.notetimeblockmanager.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.notetimeblockmanager.entity.NTBManager;
import com.tiki.server.notetimeblockmanager.repository.NTBRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RepositoryAdapter
@RequiredArgsConstructor
public class NTBFinder {

    private final NTBRepository ntbRepository;

    public List<NTBManager> findAllByNoteId(final long noteId) {
        return ntbRepository.findAllByNoteId(noteId);
    }

    public List<NTBManager> findAllByTimeBlockId(final long timeBlockId) {
        return ntbRepository.findAllByTimeBlockId(timeBlockId);
    }
}
