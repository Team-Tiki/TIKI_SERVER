package com.tiki.server.note.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.note.repository.NoteRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RepositoryAdapter
@RequiredArgsConstructor
public class NoteDeleter {

    private final NoteRepository noteRepository;

    public void deleteNoteByIds(final List<Long> noteIds){
        noteIds.forEach(noteRepository::deleteById);
    }

    public void deleteAllByTeamId(final long teamId) {
        noteRepository.deleteAllByTeamId(teamId);
    }
}
