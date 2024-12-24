package com.tiki.server.notetimeblockmanager.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.notetimeblockmanager.repository.NTBRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RepositoryAdapter
@RequiredArgsConstructor
public class NTBDeleter {

    private final NTBRepository ntbRepository;

    public void noteTimeBlockManagerDeleteByIds(final List<Long> noteIds) {
        noteIds.forEach(ntbRepository::deleteAllByNoteId);
    }

    public void deleteByNoteIdAndTimeBlockId(final long noteId, final List<Long> timeBlockIds) {
        timeBlockIds.forEach(timeBlockId ->
                ntbRepository.deleteByNoteIdAndTimeBlockId(noteId, timeBlockId)
        );
    }
}
