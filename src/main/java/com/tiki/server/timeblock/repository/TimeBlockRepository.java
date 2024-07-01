package com.tiki.server.timeblock.repository;

import org.springframework.data.repository.CrudRepository;

import com.tiki.server.timeblock.entity.TimeBlock;

public interface TimeBlockRepository extends CrudRepository<TimeBlock, Long> {
}
