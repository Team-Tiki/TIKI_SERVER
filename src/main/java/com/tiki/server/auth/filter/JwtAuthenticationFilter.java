package com.tiki.server.auth.filter;

import com.tiki.server.auth.jwt.JwtProvider;
import com.tiki.server.auth.jwt.JwtValidator;
import com.tiki.server.auth.jwt.UserAuthentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
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

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final JwtValidator jwtValidator;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws IOException, ServletException {
        val token = jwtProvider.getTokenFromRequest(request);
        if (StringUtils.hasText(token)) {
            jwtValidator.validateToken(token);
            setAuthenticationContextHolder(jwtProvider.getUserFromJwt((token)), request);
        }
        filterChain.doFilter(request, response);
    }

    private void setAuthenticationContextHolder(long memberId, HttpServletRequest request) {
        val authentication = new UserAuthentication(memberId, null, null);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
