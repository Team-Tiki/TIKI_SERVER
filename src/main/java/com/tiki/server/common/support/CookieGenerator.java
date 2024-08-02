package com.tiki.server.common.support;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class CookieGenerator {

    @Value("${JWT.EXPIRE_REFRESH}")
    private static int COOKIE_MAX_AGE;
    private final static String REFRESH_TOKEN = "refreshToken";

    public static ResponseCookie setRefreshTokenToCookie(String value){
        return ResponseCookie.from(REFRESH_TOKEN, value)
                .maxAge(COOKIE_MAX_AGE)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();
    }
}
