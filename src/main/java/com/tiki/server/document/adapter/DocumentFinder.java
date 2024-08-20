package com.tiki.server.document.adapter;

import static com.tiki.server.document.message.ErrorCode.INVALID_DOCUMENT;

import java.util.List;
import java.util.Objects;

import com.tiki.server.common.entity.Position;
import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.document.entity.Document;
import com.tiki.server.document.exception.DocumentException;
import com.tiki.server.document.repository.DocumentRepository;
import com.tiki.server.document.vo.DocumentVO;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class DocumentFinder {

	private final DocumentRepository documentRepository;

	public Document findByIdWithTimeBlock(long documentId) {
		Document document = documentRepository.findByIdWithTimeBlock(documentId);
		if (Objects.isNull(document)) {
			throw new DocumentException(INVALID_DOCUMENT);
		}
		return document;
	}

	public List<DocumentVO> findAllByTimeBlockId(long timeBlockId) {
		return documentRepository.findAllByTimeBlockId(timeBlockId).stream().map(DocumentVO::from).toList();
	}

	public List<Document> findAllByTeamIdAndAccessiblePosition(long teamId, Position accessiblePosition) {
		return documentRepository.findAllByTeamIdAndAccessiblePosition(teamId, accessiblePosition);
	}

	public List<Document> findAllByTeamId(long teamId) {
		return documentRepository.findAllByTeamId(teamId);
	}
}
