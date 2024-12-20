package com.tiki.server.timeblock.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDate;
import java.util.List;

import com.tiki.server.timeblock.entity.BlockType;
import com.tiki.server.timeblock.entity.TimeBlock;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record TimelineGetResponse(
	@NotNull List<TimeBlockGetResponse> timeBlocks
) {

	public static TimelineGetResponse from(List<TimeBlock> timeBlocks) {
		return TimelineGetResponse.builder()
			.timeBlocks(timeBlocks.stream().map(TimeBlockGetResponse::from).toList())
			.build();
	}

	@Builder(access = PRIVATE)
	public record TimeBlockGetResponse(
		@NotNull long timeBlockId,
		@NotNull String name,
		@NotNull String color,
		@NotNull LocalDate startDate,
		@NotNull LocalDate endDate,
		@NotNull BlockType blockType
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
