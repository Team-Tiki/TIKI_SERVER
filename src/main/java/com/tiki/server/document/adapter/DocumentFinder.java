package com.tiki.server.document.adapter;

import java.util.List;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.document.entity.Document;
import com.tiki.server.document.repository.DocumentRepository;
import com.tiki.server.document.vo.DocumentVO;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class DocumentFinder {

	private final DocumentRepository documentRepository;

	public List<DocumentVO> findAllByTimeBlockId(long timeBlockId) {
		return documentRepository.findAllByTimeBlockId(timeBlockId).stream().map(DocumentVO::from).toList();
	}
}
