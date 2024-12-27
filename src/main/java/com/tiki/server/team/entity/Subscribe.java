package com.tiki.server.team.entity;

import lombok.Getter;

@Getter
public enum Subscribe {
	BASIC(6000, 30000, 30, false),
	ADVANCED(13000, 150000, 120, true),
	PREMIUM(25000, 500000, 999999, true);

	private final int price;
	private final double capacity;
	private final int memberLimit;
	private final boolean bannerDiscount;

	Subscribe(final int price, final double capacity, final int memberLimit, final boolean bannerDiscount) {
		this.price = price;
		this.capacity = capacity;
		this.memberLimit = memberLimit;
		this.bannerDiscount = bannerDiscount;
	}
}
