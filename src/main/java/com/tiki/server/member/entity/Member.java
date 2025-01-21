package com.tiki.server.member.entity;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;

import com.tiki.server.common.entity.BaseTime;
import com.tiki.server.email.Email;
import com.tiki.server.common.entity.University;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member extends BaseTime {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "member_id")
	private Long id;

	private Email email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private LocalDate birth;

	@Column(nullable = false)
	@Enumerated(value = STRING)
	private University univ;

	public static Member of(
		final String email,
		final String password,
		final String name,
		final LocalDate birth,
		final University univ) {
		return Member.builder()
			.email(Email.from(email))
			.password(password)
			.name(name)
			.birth(birth)
			.univ(univ)
			.build();
	}

	public void resetPassword(final String password) {
		this.password = password;
	}
}
