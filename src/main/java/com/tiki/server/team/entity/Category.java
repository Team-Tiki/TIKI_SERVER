package com.tiki.server.team.entity;

import lombok.Getter;

@Getter
public enum Category {
	ALL("전체"),
	ARTS("예술/공연"),
	VOLUNTEER("봉사/사회활동"),
	NATURALSCIENCE("자연과학"),
	ENGINEERING("공학"),
	SOCIALSCIENCE("사회과학"),
	EMPLOYMENT("창업/취업"),
	LANGUAGE("어학"),
	SPORTS("스포츠/레저"),
	CULTURE("문화/친목"),
	RELIGION("종교"),
	OTHERS("기타");

	private final String categoryName;

	Category(String categoryName) {
		this.categoryName = categoryName;
	}
}
