package com.tiki.server.auth.jwt;

import com.tiki.server.common.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;
import static java.util.Base64.getEncoder;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    public String getTokenFromRequest(HttpServletRequest request) {
        String accessToken = request.getHeader(Constants.AUTHORIZATION);
        if (!StringUtils.hasText(accessToken) || !accessToken.startsWith(Constants.BEARER)) {
            return null;
        }
        return accessToken.substring(Constants.BEARER.length());
    }

    public long getUserFromJwt(String token) {
        Claims claims = getBodyFromJwt(token);
        return Long.parseLong(claims.get("memberId").toString());
    }

    public Claims getBodyFromJwt(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private SecretKey getSigningKey() {
        String encodedKey = getEncoder().encodeToString(secretKey.getBytes());
        return hmacShaKeyFor(encodedKey.getBytes());
    }
}
