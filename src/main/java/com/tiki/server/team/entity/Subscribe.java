package com.tiki.server.team.entity;

public enum Subscribe {
	BASIC(6000, 30000, 30, false),
	ADVANCED(13000, 150000, 120, true),
	PREMIUM(25000, 500000, 999999, true);

	private final int price;
	private final double capacity;
	private final int memberLimit;
	private final boolean bannerDiscount;

	Subscribe(int price, double capacity, int memberLimit, boolean bannerDiscount) {
		this.price = price;
		this.capacity = capacity;
		this.memberLimit = memberLimit;
		this.bannerDiscount = bannerDiscount;
	}
}
