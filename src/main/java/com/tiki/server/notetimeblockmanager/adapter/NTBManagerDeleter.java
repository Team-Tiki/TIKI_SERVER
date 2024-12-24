package com.tiki.server.notetimeblockmanager.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.notetimeblockmanager.repository.NTBManagerRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RepositoryAdapter
@RequiredArgsConstructor
public class NTBManagerDeleter {

    private final NTBManagerRepository ntbManagerRepository;

    public void noteTimeBlockManagerDeleteByIds(final List<Long> noteIds) {
        noteIds.forEach(ntbManagerRepository::deleteAllByNoteId);
    }

    public void deleteByNoteIdAndTimeBlockId(final long noteId, final List<Long> timeBlockIds) {
        timeBlockIds.forEach(timeBlockId ->
                ntbManagerRepository.deleteByNoteIdAndTimeBlockId(noteId, timeBlockId)
        );
    }
}
