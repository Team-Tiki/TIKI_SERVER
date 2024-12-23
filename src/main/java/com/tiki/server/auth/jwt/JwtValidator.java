package com.tiki.server.auth.jwt;

import com.tiki.server.auth.exception.AuthException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.tiki.server.auth.message.ErrorCode.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtValidator {

    private final JwtProvider jwtProvider;

    public void validateToken(final String token) {
        try {
            jwtProvider.getBodyFromJwt(token);
        } catch (ExpiredJwtException exception) {
            log.info(exception.getMessage());
            throw new AuthException(EXPIRED_JWT_TOKEN);
        } catch (JwtException exception) {
            log.info(exception.getMessage());
            throw new AuthException(INVALID_JWT_TOKEN);
        } catch (Exception exception) {
            log.info("예상치 못한 에러: " + exception);
            throw new AuthException(UNCAUGHT_EXCEPTION);
        }
    }
}