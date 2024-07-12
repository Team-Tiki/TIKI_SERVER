package com.tiki.server.timeblock.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDate;
import java.util.List;

import com.tiki.server.timeblock.vo.TimeBlockVO;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record TimelineGetResponse(
	List<TimeBlockGetResponse> timeBlocks
) {

	public static TimelineGetResponse from(List<TimeBlockVO> timeBlockVOs) {
		return TimelineGetResponse.builder()
			.timeBlocks(timeBlockVOs.stream().map(TimeBlockGetResponse::from).toList())
			.build();
	}

	@Builder(access = PRIVATE)
	public record TimeBlockGetResponse(
		long timeBlockId,
		@NonNull String name,
		@NonNull String color,
		@NonNull LocalDate startDate,
		@NonNull LocalDate endDate
	) {

		public static TimeBlockGetResponse from(TimeBlockVO timeBlockVO) {
			return TimeBlockGetResponse.builder()
				.timeBlockId(timeBlockVO.timeBlockId())
				.name(timeBlockVO.name())
				.color(timeBlockVO.color())
				.startDate(timeBlockVO.startDate())
				.endDate(timeBlockVO.endDate())
				.build();
		}
	}
}
