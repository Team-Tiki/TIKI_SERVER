package com.tiki.server.team.entity;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

import com.tiki.server.common.entity.BaseTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Team extends BaseTime {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "team_id")
	private Long id;

	private String name;

	private String overview;

	@Enumerated(value = STRING)
	private Category category;

	@Enumerated(value = STRING)
	private TeamType teamType;

	private String imageUrl;

	private String iconImageUrl;
}
