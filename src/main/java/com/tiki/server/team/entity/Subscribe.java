package com.tiki.server.team.entity;

import lombok.Getter;

@Getter
public enum Subscribe {
	BASIC(6000, 30L * 1073741824, 30, false),
	ADVANCED(13000, 150L * 1073741824, 120, true),
	PREMIUM(25000, 500L * 1073741824, 999999, true);

	private final int price;
	private final long capacity;
	private final int memberLimit;
	private final boolean bannerDiscount;

	Subscribe(final int price, final long capacity, final int memberLimit, final boolean bannerDiscount) {
		this.price = price;
		this.capacity = capacity;
		this.memberLimit = memberLimit;
		this.bannerDiscount = bannerDiscount;
	}
}
