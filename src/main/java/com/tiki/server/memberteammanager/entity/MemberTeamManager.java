package com.tiki.server.memberteammanager.entity;

import static com.tiki.server.memberteammanager.message.ErrorCode.*;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

import com.tiki.server.common.entity.BaseTime;
import com.tiki.server.common.entity.Position;
import com.tiki.server.member.entity.Member;
import com.tiki.server.memberteammanager.exception.MemberTeamManagerException;
import com.tiki.server.team.entity.Team;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
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

	public static MemberTeamManager of(Member member, Team team, Position position) {
		return MemberTeamManager.builder()
			.member(member)
			.team(team)
			.name(member.getName())
			.position(position)
			.build();
	}

	public void checkMemberAccessible(Position accesiblePosition) {
		if (this.position.getAuthorization() > accesiblePosition.getAuthorization()) {
			throw new MemberTeamManagerException(INVALID_AUTHORIZATION);
		}
	}

	public void setName(final String name){
		this.name = name;
	}
}
