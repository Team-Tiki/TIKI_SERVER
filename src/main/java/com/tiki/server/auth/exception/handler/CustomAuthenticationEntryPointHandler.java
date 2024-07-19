package com.tiki.server.auth.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiki.server.auth.message.ErrorCode;
import com.tiki.server.common.dto.ErrorResponse;
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
        log.info("-EntryPoint-");
        setResponse(response, ErrorCode.UNAUTHENTICATED_USER.getMessage());
    }

    private void setResponse(HttpServletResponse response, String errorMessage) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        val writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(ErrorResponse.of(errorMessage)));
    }
}
