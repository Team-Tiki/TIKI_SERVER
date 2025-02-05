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

	public List<DTBManager> getAllByTimeBlock(final TimeBlock timeBlock) {
		return dtbRepository.findAllByTimeBlockId(timeBlock.getId());
	}

	public List<DTBManager> getAllByIds(final List<Long> ids) {
		return dtbRepository.findAllByIdIn(ids);
	}

	public void deleteAll(final List<DTBManager> dtbManagers) {
		dtbRepository.deleteAll(dtbManagers);
	}

	public void deleteAllByTimeBlock(final TimeBlock timeBlock) {
		dtbRepository.deleteAllByTimeBlockId(timeBlock.getId());
	}

	public void deleteAllByDocuments(final List<Long> documentIds) {
		dtbRepository.deleteAllByDocumentIdIn(documentIds);
	}
}
