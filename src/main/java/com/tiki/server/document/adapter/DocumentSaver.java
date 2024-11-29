package com.tiki.server.document.adapter;

import java.util.List;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.document.entity.DeletedDocument;
import com.tiki.server.document.entity.Document;
import com.tiki.server.document.repository.DocumentRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class DocumentSaver {

	private final DocumentRepository documentRepository;

	public Document save(final Document document) {
		return documentRepository.save(document);
	}

	public void restore(final List<DeletedDocument> deletedDocuments) {
		deletedDocuments.forEach(document -> documentRepository.save(create(document)));
	}

	private Document create(final DeletedDocument deletedDocument) {
		return Document.restore(
				deletedDocument.getFileName(),
				deletedDocument.getFileUrl(),
				deletedDocument.getCapacity(),
				deletedDocument.getTeamId()
		);
	}
}
