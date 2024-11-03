package com.tiki.server.note.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.note.entity.NoteFree;
import com.tiki.server.note.repository.NoteFreeRepository;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class NoteFreeSaver {

    private final NoteFreeRepository noteFreeRepository;

    public NoteFree createNoteFree(final NoteFree noteFree){
        return noteFreeRepository.save(noteFree);
    }
}
