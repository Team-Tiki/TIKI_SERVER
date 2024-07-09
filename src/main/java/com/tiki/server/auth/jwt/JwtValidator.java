package com.tiki.server.auth.jwt;

import com.tiki.server.auth.exception.AuthException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.tiki.server.auth.jwt.JwtValidationType.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtValidator {
    private final JwtGenerator jwtGenerator;

    public JwtValidationType validateToken(String token) {
        try {
            jwtGenerator.getBody(token);
            return VALID_JWT;
        } catch(MalformedJwtException exception) {
            log.error(exception.getMessage());
            return INVALID_JWT_TOKEN;
        } catch (ExpiredJwtException exception) {
            log.error(exception.getMessage());
            return EXPIRED_JWT_TOKEN;
        } catch (UnsupportedJwtException exception) {
            log.error(exception.getMessage());
            return UNSUPPORTED_JWT_TOKEN;
        } catch (IllegalArgumentException exception) {
            log.error(exception.getMessage());
            return EMPTY_JWT;
        }
    }

}