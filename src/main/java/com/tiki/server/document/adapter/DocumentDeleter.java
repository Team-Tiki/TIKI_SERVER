package com.tiki.server.document.adapter;

import java.util.List;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.document.entity.Document;
import com.tiki.server.document.repository.DocumentRepository;
import com.tiki.server.document.vo.DocumentVO;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class DocumentDeleter {

	private final DocumentRepository documentRepository;

	public void delete(Document document) {
		documentRepository.delete(document);
	}

	public void deleteAllById(List<DocumentVO> documents) {
		documents.forEach(documentVO -> documentRepository.deleteById(documentVO.documentId()));
	}
}
