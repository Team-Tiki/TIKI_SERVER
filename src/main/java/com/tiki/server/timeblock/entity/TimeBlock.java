package com.tiki.server.timeblock.entity;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

import java.time.LocalDate;

import com.tiki.server.common.entity.BaseTime;
import com.tiki.server.common.entity.Position;
import com.tiki.server.team.entity.Team;
import com.tiki.server.timeblock.dto.request.TimeBlockCreateRequest;
import com.tiki.server.timeblock.dto.request.TimeBlockUpdateRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class TimeBlock extends BaseTime {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "block_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String color;

	@Column(nullable = false)
	@Enumerated(value = STRING)
	private Position accessiblePosition;

	@Column(nullable = false)
	private LocalDate startDate;

	@Column(nullable = false)
	private LocalDate endDate;

	@Column(nullable = false)
	@Enumerated(value = STRING)
	private BlockType type;

	@Column(nullable = false)
	private long teamId;

	public static TimeBlock of(final Team team, final Position accessiblePosition,
		final TimeBlockCreateRequest request) {
		return TimeBlock.builder()
			.name(request.name())
			.color(request.color())
			.accessiblePosition(accessiblePosition)
			.startDate(request.startDate())
			.endDate(request.endDate())
			.teamId(team.getId())
			.type(request.blockType())
			.build();
	}

	public void updateNameAndDate(final TimeBlockUpdateRequest request) {
		this.name = request.name();
		this.startDate = request.startDate();
		this.endDate = request.endDate();
	}
}
