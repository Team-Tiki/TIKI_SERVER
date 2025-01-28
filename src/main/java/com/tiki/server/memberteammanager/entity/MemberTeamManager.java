package com.tiki.server.memberteammanager.entity;

import static com.tiki.server.memberteammanager.message.ErrorCode.*;
import static jakarta.persistence.EnumType.STRING;
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

	@Column(nullable = false)
	private long memberId;

	@Column(nullable = false)
	private long teamId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	@Enumerated(value = STRING)
	private Position position;

	public static MemberTeamManager of(final Member member, final Team team, final Position position) {
		return MemberTeamManager.builder()
			.memberId(member.getId())
			.teamId(team.getId())
			.name(member.getName())
			.position(position)
			.build();
	}

	public void checkMemberAccessible(final Position accesiblePosition) {
		if (this.position.getAuthorization() > accesiblePosition.getAuthorization()) {
			throw new MemberTeamManagerException(INVALID_AUTHORIZATION);
		}
	}

	public void checkMemberIsNotAdmin() {
		if (Position.isAdmin(this.position)) {
			throw new MemberTeamManagerException(CANNOT_QUIT_TEAM);
		}
	}

	public void updateName(final String name){
		this.name = name;
	}

	public void updatePositionToExecutive(){
		this.position = Position.EXECUTIVE;
	}

	public void updatePositionToAdmin(){
		this.position = Position.ADMIN;
	}
}
