package com.tiki.server.folder.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.folder.repository.FolderRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class FolderFinder {

	private final FolderRepository folderRepository;
}
