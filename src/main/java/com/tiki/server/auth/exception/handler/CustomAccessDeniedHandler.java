package com.tiki.server.auth.exception.handler;

import com.tiki.server.auth.info.AuthenticationResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.tiki.server.auth.message.ErrorCode.UNAUTHORIZED_USER;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private AuthenticationResponse authenticationResponse;

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {
        authenticationResponse.makeFailureResponse(response, UNAUTHORIZED_USER);
    }
}
