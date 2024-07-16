package com.tiki.server.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;

import static com.tiki.server.auth.token.constants.TokenConstants.REFRESH_TOKEN_EXPIRED_TIME;

public class CookieUtil {

    public static void addRefreshToken(HttpServletResponse response, String value){

        Cookie cookie = new Cookie("refresh_token", value);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(REFRESH_TOKEN_EXPIRED_TIME);
        response.addCookie(cookie);
    }

}
