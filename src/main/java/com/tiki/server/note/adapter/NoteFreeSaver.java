package com.tiki.server.note.adapter;

import com.tiki.server.note.entity.NoteFree;
import com.tiki.server.note.repository.NoteFreeRepository;

public class NoteFreeSaver {

    NoteFreeRepository noteFreeRepository;

    public NoteFree createNoteFree(NoteFree noteFree){
        return noteFreeRepository.save(noteFree);
    }
}
