package com.tiki.server.notetimeblockmanager.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.notetimeblockmanager.repository.NoteTimeBlockManagerRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RepositoryAdapter
@RequiredArgsConstructor
public class NoteTimeBlockManagerDeleter {

    private final NoteTimeBlockManagerRepository noteTimeBlockManagerRepository;

    public void noteTimeBlockManagerDeleteByIds(final List<Long> noteIds) {
        noteIds.forEach(noteTimeBlockManagerRepository::deleteAllByNoteId);
    }

    public void deleteByNoteIdAndTimeBlockId(final long noteId, final List<Long> timeBlockIds) {
        timeBlockIds.forEach(timeBlockId ->
                noteTimeBlockManagerRepository.deleteByNoteIdAndTimeBlockId(noteId, timeBlockId)
        );
    }
}
