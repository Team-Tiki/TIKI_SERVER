package com.tiki.server.timeblock.entity;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;

import com.tiki.server.common.entity.BaseTime;
import com.tiki.server.common.entity.Position;
import com.tiki.server.team.entity.Team;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class TimeBlock extends BaseTime {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "block_id")
	private Long id;

	private String name;

	private String color;

	@Enumerated(value = STRING)
	private Position accessiblePosition;

	private LocalDate startDate;

	private LocalDate endDate;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "team_id")
	private Team team;

	@Builder
	public static TimeBlock of(
		String name,
		String color,
		Position accessiblePosition,
		LocalDate startDate,
		LocalDate endDate,
		Team team
	) {
		return TimeBlock.builder()
			.name(name)
			.color(color)
			.accessiblePosition(accessiblePosition)
			.startDate(startDate)
			.endDate(endDate)
			.team(team)
			.build();
	}
}
