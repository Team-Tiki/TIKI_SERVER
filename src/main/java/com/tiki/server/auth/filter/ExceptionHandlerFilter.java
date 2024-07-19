package com.tiki.server.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiki.server.auth.exception.AuthException;
import com.tiki.server.auth.message.ErrorCode;
import com.tiki.server.common.dto.ErrorResponse;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (AuthException e) {
            log.info("ExceptionHandlerFilter: AuthException - " + e);
            handleAuthException(response, e);
        } catch (JwtException e) {
            log.info("ExceptionHandlerFilter: JWTException - " + e);
            handleJwtException(response);
        } catch (Exception e) {
            log.info("ExceptionHandlerFilter: Exception - " + e);
            handleUncaughtException(response, e);
        }
    }

    private void handleAuthException(HttpServletResponse response, AuthException e) throws IOException {
        val errorMessage = e.getErrorCode().getMessage();
        val httpStatus = e.getErrorCode().getHttpStatus();
        setResponse(response, httpStatus, errorMessage);
    }

    private void handleJwtException(HttpServletResponse response) throws IOException {
        val jwtException = ErrorCode.INVALID_JWT_TOKEN;
        setResponse(response, jwtException.getHttpStatus(), jwtException.getMessage());
    }

    private void handleUncaughtException(HttpServletResponse response, Exception e) throws IOException {
        val uncaughtException = ErrorCode.UNCAUGHT_EXCEPTION;
        setResponse(response, uncaughtException.getHttpStatus(), uncaughtException.getMessage());
    }

    private void setResponse(HttpServletResponse response, HttpStatus httpStatus, String errorMessage) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(httpStatus.value());
        val writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(ErrorResponse.of(errorMessage)));
    }
}
