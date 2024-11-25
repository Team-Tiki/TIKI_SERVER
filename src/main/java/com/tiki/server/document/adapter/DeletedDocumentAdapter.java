package com.tiki.server.document.adapter;

import java.util.List;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.document.entity.DeletedDocument;
import com.tiki.server.document.entity.Document;
import com.tiki.server.document.repository.DeletedDocumentRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class DeletedDocumentAdapter {

	private final DeletedDocumentRepository deletedDocumentRepository;

	public void save(final List<Document> documents, final long teamId) {
		documents.forEach(document -> deletedDocumentRepository.save(create(document, teamId)));
	}

	private DeletedDocument create(final Document document, final long teamId) {
		return DeletedDocument.of(document.getFileName(), document.getFileUrl(), teamId, document.getCapacity());
	}
}
