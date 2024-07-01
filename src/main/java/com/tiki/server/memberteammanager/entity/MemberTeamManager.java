package com.tiki.server.memberteammanager.entity;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import com.tiki.server.common.entity.BaseTime;
import com.tiki.server.member.entity.Member;
import com.tiki.server.team.entity.Team;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MemberTeamManager extends BaseTime {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "manager_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "team_id")
	private Team team;

	private String name;

	@Enumerated(value = STRING)
	private Position position;
}
