package com.tiki.server.auth.exception.handler;

import static com.tiki.server.auth.message.ErrorCode.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiki.server.common.dto.ErrorCodeResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        log.info("[AuthenticationEntryPoint] " + authException.getMessage());
        setResponse(response);
    }

    private void setResponse(HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(
            ErrorCodeResponse.of(UNAUTHENTICATED.getCode(), UNAUTHENTICATED.getMessage())));
    }
}
