package com.tiki.server.note.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.note.entity.Note;
import com.tiki.server.note.exception.NoteException;
import com.tiki.server.note.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

import static com.tiki.server.note.message.ErrorCode.INVALID_NOTE;

@RepositoryAdapter
@RequiredArgsConstructor
public class NoteFinder {

    private final NoteRepository noteRepository;

    public List<Note> findByCreatedAtBeforeOrderByModifiedAtDesc(final LocalDateTime createdAt, final PageRequest pageRequest) {
        return noteRepository.findByCreatedAtBeforeOrderByModifiedAtDesc(createdAt, pageRequest);
    }

    public List<Note> findByCreatedAtAfterOrderByModifiedAtAsc(final LocalDateTime createdAt, final PageRequest pageRequest) {
        return noteRepository.findByCreatedAtAfterOrderByModifiedAtAsc(createdAt, pageRequest);
    }

    public Note findById(final long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new NoteException(INVALID_NOTE));
    }
}