package com.tiki.server.auth.filter;

import static com.tiki.server.auth.message.ErrorCode.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiki.server.auth.exception.AuthException;
import com.tiki.server.auth.message.ErrorCode;
import com.tiki.server.common.dto.ErrorCodeResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

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
            log.info("[ExceptionHandlerFilter] - AuthException : " + e);
            setResponse(response, e.getErrorCode());
        } catch (Exception e) {
            log.info("[ExceptionHandlerFilter] - UncaughtException : " + e);
            setResponse(response, UNCAUGHT_EXCEPTION);
        }
    }

    private void setResponse(HttpServletResponse response, ErrorCode errorCode)
            throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(errorCode.getHttpStatus().value());
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(ErrorCodeResponse.of(errorCode.getCode(), errorCode.getMessage())));
    }
}
