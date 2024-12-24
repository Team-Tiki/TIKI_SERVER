package com.tiki.server.notetimeblockmanager.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.notetimeblockmanager.entity.NTBManager;
import com.tiki.server.notetimeblockmanager.repository.NTBManagerRepository;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class NTBManagerSaver {

    private final NTBManagerRepository ntbManagerRepository;

    public NTBManager save(final NTBManager ntbManager) {
        return ntbManagerRepository.save(ntbManager);
    }
}
