package com.tiki.server.auth.info;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiki.server.auth.message.ErrorCode;
import com.tiki.server.common.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@RequiredArgsConstructor
public class AuthenticationResponse {

    private final ObjectMapper objectMapper;

    public void makeFailureResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(errorCode.getHttpStatus().value());
        response.getWriter().println(objectMapper.writeValueAsString(ErrorResponse.of(errorCode.getMessage())));
    }
}
