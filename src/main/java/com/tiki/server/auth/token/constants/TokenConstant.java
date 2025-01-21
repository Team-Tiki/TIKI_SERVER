package com.tiki.server.auth.token.constants;

import org.springframework.beans.factory.annotation.Value;

public class TokenConstant {

    @Value("${jwt.refresh-token-expire-time}")
    public static int REFRESH_TOKEN_EXPIRED_TIME;
}
