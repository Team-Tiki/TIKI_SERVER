package com.tiki.server.notedocumentmanager.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.notedocumentmanager.entity.NDManager;
import com.tiki.server.notedocumentmanager.repository.NDManagerRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RepositoryAdapter
@RequiredArgsConstructor
public class NDManagerFinder {

    private final NDManagerRepository ndManagerRepository;

    public List<NDManager> findAllByNoteId(final long noteId){
        return ndManagerRepository.findAllByNoteId(noteId);
    }
}