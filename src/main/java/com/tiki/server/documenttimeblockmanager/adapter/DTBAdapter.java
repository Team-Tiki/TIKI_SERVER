package com.tiki.server.documenttimeblockmanager.adapter;

import java.util.List;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.documenttimeblockmanager.entity.DTBManager;
import com.tiki.server.documenttimeblockmanager.repository.DTBRepository;
import com.tiki.server.timeblock.entity.TimeBlock;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class DTBAdapter {

	private final DTBRepository dtbRepository;

	public void saveAll(final TimeBlock timeBlock, final List<Long> documentIds) {
		documentIds.forEach(documentId -> dtbRepository.save(DTBManager.of(timeBlock, documentId)));
	}
}
