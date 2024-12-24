package com.tiki.server.notetimeblockmanager.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.notetimeblockmanager.entity.NTBManager;
import com.tiki.server.notetimeblockmanager.repository.NTBRepository;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class NTBSaver {

    private final NTBRepository ntbRepository;

    public NTBManager save(final NTBManager ntbManager) {
        return ntbRepository.save(ntbManager);
    }
}
