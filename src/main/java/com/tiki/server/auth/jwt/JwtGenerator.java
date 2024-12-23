package com.tiki.server.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

import static io.jsonwebtoken.Header.JWT_TYPE;
import static io.jsonwebtoken.Header.TYPE;
import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;
import static java.util.Base64.getEncoder;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtGenerator {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access-token-expire-time}")
    private long ACCESS_TOKEN_EXPIRE_TIME;

    @Value("${jwt.refresh-token-expire-time}")
    public long REFRESH_TOKEN_EXPIRE_TIME;

    public String generateToken(final Authentication authentication, final long expiration) {
        return Jwts.builder()
                .setHeaderParam(TYPE, JWT_TYPE)
                .setClaims(generateClaims(authentication))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    public String generateAccessToken(final Authentication authentication) {
        return generateToken(authentication, ACCESS_TOKEN_EXPIRE_TIME);
    }

    public String generateRefreshToken(final Authentication authentication) {
        return generateToken(authentication, REFRESH_TOKEN_EXPIRE_TIME);
    }

    private Claims generateClaims(final Authentication authentication) {
        Claims claims = Jwts.claims();
        claims.put("memberId", authentication.getPrincipal());
        return claims;
    }

    private SecretKey getSigningKey() {
        String encodedKey = getEncoder().encodeToString(secretKey.getBytes());
        return hmacShaKeyFor(encodedKey.getBytes());
    }
}
