package com.tiki.server.auth.jwt;

import com.tiki.server.auth.dto.response.LoginResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.val;
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
	private long REFRESH_TOKEN_EXPIRE_TIME;
	public LoginResponse generateAllToken(Authentication authentication){
		return LoginResponse.of(
				generateToken(authentication,ACCESS_TOKEN_EXPIRE_TIME),
				generateToken(authentication,REFRESH_TOKEN_EXPIRE_TIME)
		);
	}

	public String generateToken(Authentication authentication, long expiration) {
		return Jwts.builder()
			.setHeaderParam(TYPE, JWT_TYPE)
			.setClaims(generateClaims(authentication))
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + expiration))
			.signWith(getSigningKey())
			.compact();
	}

	private Claims generateClaims(Authentication authentication) {
		val claims = Jwts.claims();
		claims.put("memberId", authentication.getPrincipal());
		return claims;
	}

	private SecretKey getSigningKey() {
		val encodedKey = getEncoder().encodeToString(secretKey.getBytes());
		return hmacShaKeyFor(encodedKey.getBytes());
	}
}
