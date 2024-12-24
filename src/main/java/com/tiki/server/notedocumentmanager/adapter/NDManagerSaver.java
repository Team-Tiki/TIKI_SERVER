package com.tiki.server.notedocumentmanager.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.notedocumentmanager.entity.NDManager;
import com.tiki.server.notedocumentmanager.repository.NDManagerRepository;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class NDManagerSaver {

    private final NDManagerRepository ndManagerRepository;

    public NDManager save(final NDManager ndManager) {
        return ndManagerRepository.save(ndManager);
    }
}
