package com.tiki.server.common;

import org.springframework.beans.factory.annotation.Value;


public class Constants {

    @Value("${jwt.secret}")
    private String JWT_SECRET_KEY;

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
}
