package com.tiki.server.auth.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiki.server.auth.info.AuthenticationResponse;
import com.tiki.server.auth.message.ErrorCode;
import com.tiki.server.common.dto.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.tiki.server.auth.message.ErrorCode.UNAUTHENTICATED_USER;

@Component
public class CustomAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    AuthenticationResponse authenticationResponse;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        authenticationResponse.makeFailureResponse(response, UNAUTHENTICATED_USER);
    }
}
