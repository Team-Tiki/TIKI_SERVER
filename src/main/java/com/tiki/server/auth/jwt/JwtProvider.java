package com.tiki.server.auth.jwt;


import com.tiki.server.common.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;
import static java.util.Base64.getEncoder;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    public long getUserFromJwt(String token) {
        val claims = getBody(token);
        return Long.parseLong(claims.get("memberId").toString());
    }

    public Claims getBody(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private SecretKey getSigningKey() {
        val encodedKey = getEncoder().encodeToString(secretKey.getBytes());
        return hmacShaKeyFor(encodedKey.getBytes());
    }
}
