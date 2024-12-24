package com.tiki.server.notedocumentmanager.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.notedocumentmanager.entity.NDManager;
import com.tiki.server.notedocumentmanager.repository.NDRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RepositoryAdapter
@RequiredArgsConstructor
public class NDFinder {

    private final NDRepository ndRepository;

    public List<NDManager> findAllByNoteId(final long noteId){
        return ndRepository.findAllByNoteId(noteId);
    }
}