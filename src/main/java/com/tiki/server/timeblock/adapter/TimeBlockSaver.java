package com.tiki.server.timeblock.adapter;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.timeblock.entity.TimeBlock;
import com.tiki.server.timeblock.repository.TimeBlockRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class TimeBlockSaver {

	private final TimeBlockRepository timeBlockRepository;

	public TimeBlock save(TimeBlock timeBlock) {
		return timeBlockRepository.save(timeBlock);
	}
}
