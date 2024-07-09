package com.tiki.server.auth.filter;


import com.tiki.server.auth.exception.AuthException;
import com.tiki.server.auth.jwt.JwtProvider;
import com.tiki.server.auth.jwt.JwtValidator;
import com.tiki.server.auth.jwt.UserAuthentication;
import com.tiki.server.common.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.tiki.server.auth.jwt.JwtValidationType.VALID_JWT;
import static com.tiki.server.auth.message.ErrorCode.INVALID_KEY;
import static io.jsonwebtoken.lang.Strings.hasText;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final JwtValidator jwtValidator;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain
    ) throws IOException {
        try{
            val token = getAccessTokenFromRequest(request);
            if (hasText(token) && jwtValidator.validateToken(token) == VALID_JWT){
                val authentication = new UserAuthentication(jwtProvider.getUserFromJwt(token), null, null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }catch (Exception e){

        }
    }

    private String getAccessTokenFromRequest(HttpServletRequest request) {
        String accessToken = request.getHeader(Constants.AUTHORIZATION);
        if (!StringUtils.hasText(accessToken) || !accessToken.startsWith(Constants.BEARER)) {
            throw new AuthException(INVALID_KEY);
        }
        return accessToken.substring(Constants.BEARER.length());
    }
}
