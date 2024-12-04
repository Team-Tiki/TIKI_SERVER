package com.tiki.server.team.entity;

import static com.tiki.server.common.Constants.INIT_NUM;
import static com.tiki.server.team.entity.Subscribe.*;
import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.*;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@Builder(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class SubscribeInfo {

	@Enumerated(value = STRING)
	private Subscribe subscribe;

	private double usage;

	protected static SubscribeInfo createBasicSubscribe() {
		return SubscribeInfo.builder()
			.subscribe(BASIC)
			.usage(INIT_NUM)
			.build();
	}
}
