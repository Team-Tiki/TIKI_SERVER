package com.tiki.server.timeblock.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.timeblock.repository.TimeBlockRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class TimeBlockFinder {

	private final TimeBlockRepository timeBlockRepository;
}
