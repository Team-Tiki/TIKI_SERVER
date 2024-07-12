package com.tiki.server.timeblock.vo;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDate;

import com.tiki.server.timeblock.entity.TimeBlock;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record TimeBlockVO(
	long timeBlockId,
	@NonNull String name,
	@NonNull String color,
	@NonNull LocalDate startDate,
	@NonNull LocalDate endDate
) {

	public static TimeBlockVO from(TimeBlock timeBlock) {
		return TimeBlockVO.builder()
			.timeBlockId(timeBlock.getId())
			.name(timeBlock.getName())
			.color(timeBlock.getColor())
			.startDate(timeBlock.getStartDate())
			.endDate(timeBlock.getEndDate())
			.build();
	}
}
