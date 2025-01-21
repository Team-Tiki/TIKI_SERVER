package com.tiki.server.auth.utils;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;

import static com.tiki.server.auth.token.constants.TokenConstant.REFRESH_TOKEN_EXPIRED_TIME;

public class CookieUtil {

    public static void addRefreshToken(HttpServletResponse response, String value) {

        ResponseCookie cookie = ResponseCookie.from("refreshToken", value)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .maxAge(REFRESH_TOKEN_EXPIRED_TIME)
                .build();
        response.setHeader("Set-Cookie", cookie.toString());
    }

}
