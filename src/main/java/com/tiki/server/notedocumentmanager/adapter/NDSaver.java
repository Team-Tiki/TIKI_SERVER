package com.tiki.server.notedocumentmanager.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.notedocumentmanager.entity.NDManager;
import com.tiki.server.notedocumentmanager.repository.NDRepository;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class NDSaver {

    private final NDRepository ndRepository;

    public NDManager save(final NDManager ndManager) {
        return ndRepository.save(ndManager);
    }
}
