package com.tiki.server.note.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.note.entity.Note;
import com.tiki.server.note.repository.NoteRepository;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class NoteSaver {

    private final NoteRepository noteRepository;

    public Note createNote(final Note note) {
        return noteRepository.save(note);
    }
}
