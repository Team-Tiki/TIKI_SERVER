package com.tiki.server.timeblock.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDate;
import java.util.List;

import com.tiki.server.timeblock.entity.BlockType;
import com.tiki.server.timeblock.entity.TimeBlock;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record TimelineGetResponse(
	List<TimeBlockGetResponse> timeBlocks
) {

	public static TimelineGetResponse from(List<TimeBlock> timeBlocks) {
		return TimelineGetResponse.builder()
			.timeBlocks(timeBlocks.stream().map(TimeBlockGetResponse::from).toList())
			.build();
	}

	@Builder(access = PRIVATE)
	public record TimeBlockGetResponse(
		long timeBlockId,
		@NonNull String name,
		@NonNull String color,
		@NonNull LocalDate startDate,
		@NonNull LocalDate endDate,
		@NonNull BlockType blockType
		) {

		public static TimeBlockGetResponse from(TimeBlock timeBlock) {
			return TimeBlockGetResponse.builder()
				.timeBlockId(timeBlock.getId())
				.name(timeBlock.getName())
				.color(timeBlock.getColor())
				.startDate(timeBlock.getStartDate())
				.endDate(timeBlock.getEndDate())
				.blockType(timeBlock.getType())
				.build();
		}
	}
}
