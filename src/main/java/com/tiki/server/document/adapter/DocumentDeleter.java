package com.tiki.server.document.adapter;

import java.util.List;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.document.entity.Document;
import com.tiki.server.document.repository.DocumentRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class DocumentDeleter {

	private final DocumentRepository documentRepository;

	public void deleteAll(final List<Document> documents) {
		documentRepository.deleteAll(documents);
	}
}
