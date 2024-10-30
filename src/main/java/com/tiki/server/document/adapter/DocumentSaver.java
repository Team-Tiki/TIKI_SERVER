package com.tiki.server.document.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.document.entity.Document;
import com.tiki.server.document.repository.DocumentRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class DocumentSaver {

	private final DocumentRepository documentRepository;

	public Document save(Document document) {
		return documentRepository.save(document);
	}
}
