package com.tiki.server.document.adapter;

import static com.tiki.server.document.message.ErrorCode.INVALID_DOCUMENT;

import java.util.List;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.document.entity.Document;
import com.tiki.server.document.exception.DocumentException;
import com.tiki.server.document.repository.DocumentRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class DocumentFinder {

    private final DocumentRepository documentRepository;

    public List<Document> findAllByIdAndTeamId(final List<Long> documentIds, final long teamId) {
        return documentIds.stream()
                .map(id -> findByIdAndTeamId(id, teamId))
                .toList();
    }

    public Document findById(final long documentId) {
        return documentRepository.findById(documentId)
            .orElseThrow(() -> new DocumentException(INVALID_DOCUMENT));
    }

    public List<Document> findAllByTeamId(long teamId) {
        return documentRepository.findAllByTeamId(teamId);
    }

    public boolean existsById(Long timeBlockId) {
        return documentRepository.existsById(timeBlockId);
    }

    public List<Document> findByTeamIdAndFolderId(final long teamId, final Long folderId) {
        return documentRepository.findAllByTeamIdAndFolderIdOrderByCreatedAtDesc(teamId, folderId);
    }

    public List<Document> findAllByFolderId(final long folderId) {
        return documentRepository.findAllByFolderId(folderId);
    }

    private Document findByIdAndTeamId(long documentId, long teamId) {
        return documentRepository.findByIdAndTeamId(documentId, teamId)
                .orElseThrow(() -> new DocumentException(INVALID_DOCUMENT));
    }
}
