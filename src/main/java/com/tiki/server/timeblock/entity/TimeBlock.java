package com.tiki.server.timeblock.entity;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;
import java.util.List;

import com.tiki.server.common.entity.BaseTime;
import com.tiki.server.team.entity.Team;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "team_id")
	private Team team;

	private String color;

	private String name;

	private LocalDate startDate;

	private LocalDate endDate;

	/* StringListConverter 필요 */
	private List<String> filesUrl;
}
