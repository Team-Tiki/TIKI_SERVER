package com.tiki.server.document.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.document.entity.Document;
import com.tiki.server.document.repository.DocumentRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class DocumentCreator {

	private final DocumentRepository documentRepository;

	public Document create(Document document) {
		return documentRepository.save(document);
	}
}
