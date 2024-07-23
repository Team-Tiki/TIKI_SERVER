package com.tiki.server.timeblock.adapter;

import java.util.HashSet;
import java.util.List;

import com.tiki.server.common.support.RepositoryAdapter;
import com.tiki.server.timeblock.entity.TimeBlock;
import com.tiki.server.timeblock.repository.TimeBlockRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class TimeBlockDeleter {

	private final TimeBlockRepository timeBlockRepository;

	public void deleteById(long id) {
		timeBlockRepository.deleteById(id);
	}

	public void deleteAll(HashSet<TimeBlock> timeBlocks) {
		timeBlockRepository.deleteAll(timeBlocks);
	}
}
