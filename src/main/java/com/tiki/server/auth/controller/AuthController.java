package com.tiki.server.auth.controller;

import com.tiki.server.auth.dto.request.LoginRequest;
import com.tiki.server.auth.dto.response.ReissueGetResponse;
import com.tiki.server.common.dto.SuccessResponse;
import com.tiki.server.common.support.UriGenerator;
import com.tiki.server.member.dto.response.AccessTokenGetResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tiki.server.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

import static com.tiki.server.auth.message.SuccessMessage.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    @Value("${JWT.EXPIRE_REFRESH}")
    private int COOKIE_MAX_AGE;
    private final static String REFRESH_TOKEN = "refreshToken";
    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<SuccessResponse<AccessTokenGetResponse>> signIn(
            HttpServletResponse httpServletResponse,
            @RequestBody LoginRequest request
    ) {
        val response = authService.login(request, httpServletResponse);
        ResponseCookie cookie = ResponseCookie.from(REFRESH_TOKEN, response.refreshToken())
                .maxAge(COOKIE_MAX_AGE)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();
        httpServletResponse.setHeader("Set-Cookie", cookie.toString());
        return ResponseEntity.created(UriGenerator.getUri("/"))
                .body(SuccessResponse.success(SUCCESS_SIGN_IN.getMessage(),
                        AccessTokenGetResponse.of(response.accessToken())));
    }

    @GetMapping("/reissue")
    public ResponseEntity<SuccessResponse<ReissueGetResponse>> reissue(HttpServletRequest httpServletRequest) {
        val response = authService.reissueToken(httpServletRequest);
        return ResponseEntity.created(UriGenerator.getUri("/"))
                .body(SuccessResponse.success(SUCCESS_REISSUE_ACCESS_TOKEN.getMessage(), response));
    }
}
