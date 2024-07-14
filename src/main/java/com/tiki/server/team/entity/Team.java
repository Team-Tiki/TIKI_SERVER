package com.tiki.server.team.entity;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

import com.tiki.server.common.entity.BaseTime;
import com.tiki.server.common.entity.University;
import com.tiki.server.team.dto.request.TeamCreateRequest;

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
	private University univ;

	private String imageUrl;

	private String iconImageUrl;

	public static Team of(TeamCreateRequest request, University univ) {
		return Team.builder()
			.name(request.name())
			.category(request.category())
			.univ(univ)
			.iconImageUrl(request.iconImageUrl())
			.build();
	}
}
