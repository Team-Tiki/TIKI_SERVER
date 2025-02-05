package com.tiki.server.document.adapter;

import static com.tiki.server.document.message.ErrorCode.INVALID_DOCUMENT;

import java.util.List;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.document.entity.DeletedDocument;
import com.tiki.server.document.entity.Document;
import com.tiki.server.document.exception.DocumentException;
import com.tiki.server.document.repository.DeletedDocumentRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class DeletedDocumentAdapter {

	private final DeletedDocumentRepository deletedDocumentRepository;

	public List<DeletedDocument> get(final long teamId) {
		return deletedDocumentRepository.findAllByTeamId(teamId);
	}

	public void save(final List<Document> documents) {
		documents.forEach(document -> deletedDocumentRepository.save(create(document)));
	}

	public List<DeletedDocument> get(final List<Long> deletedDocumentIds, final long teamId) {
		return deletedDocumentIds.stream()
				.map(id -> find(id, teamId))
				.toList();
	}

	public void deleteAll(final List<DeletedDocument> deletedDocuments) {
		deletedDocumentRepository.deleteAll(deletedDocuments);
	}

	public void deleteAllByTeamId(final long teamId) {
		deletedDocumentRepository.deleteAllByTeamId(teamId);
	}

	private DeletedDocument create(final Document document) {
		return DeletedDocument.of(document);
	}

	private DeletedDocument find(final long id, final long teamId) {
		return deletedDocumentRepository.findByIdAndTeamId(id, teamId)
				.orElseThrow(() -> new DocumentException(INVALID_DOCUMENT));
	}
}
