package com.tiki.server.document.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.document.repository.DocumentRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class DocumentFinder {

	private final DocumentRepository documentRepository;
}
