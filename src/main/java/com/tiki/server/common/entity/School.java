package com.tiki.server.common.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum School {
    KONKUK_UNIV("건국대학교");

    private final String school;
}
