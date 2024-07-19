package com.tiki.server.auth.jwt;

import com.tiki.server.auth.exception.AuthException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.tiki.server.auth.message.ErrorCode.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtValidator {

    private final JwtProvider jwtProvider;

    public void validateToken(String token) {
        try {
            jwtProvider.getBodyFromJwt(token);
        } catch (MalformedJwtException exception) {
            log.info(exception.getMessage());
            throw new AuthException(INVALID_JWT_TOKEN);
        } catch (ExpiredJwtException exception) {
            log.info(exception.getMessage());
            throw new AuthException(EXPIRED_JWT_TOKEN);
        } catch (UnsupportedJwtException exception) {
            log.info(exception.getMessage());
            throw new AuthException(UNSUPPORTED_JWT_TOKEN);
        }
    }
}