package com.tiki.server.auth.service;

import com.tiki.server.auth.dto.request.LoginRequest;
import com.tiki.server.auth.dto.response.UserAllTokenGetResponse;
import com.tiki.server.auth.dto.response.UserTokenGetResponse;
import com.tiki.server.auth.exception.AuthException;
import com.tiki.server.auth.jwt.JwtProvider;
import com.tiki.server.auth.token.adapter.TokenFinder;
import com.tiki.server.auth.token.adapter.TokenSaver;
import com.tiki.server.auth.token.entity.Token;
import com.tiki.server.member.adapter.MemberFinder;
import com.tiki.server.member.entity.Member;
import com.tiki.server.member.exception.MemberException;
import com.tiki.server.utils.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiki.server.auth.jwt.JwtGenerator;
import com.tiki.server.auth.jwt.UserAuthentication;

import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.Optional;

import static com.tiki.server.auth.message.ErrorCode.UNAUTHORIZED_USER;
import static com.tiki.server.auth.message.ErrorCode.UNMATCHED_TOKEN;
import static com.tiki.server.member.message.ErrorCode.INVALID_MEMBER;
import static com.tiki.server.member.message.ErrorCode.UNMATCHED_PASSWORD;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final JwtGenerator jwtGenerator;
    private final JwtProvider jwtProvider;
    private final MemberFinder memberFinder;
    private final TokenSaver tokenSaver;
    private final TokenFinder tokenFinder;
    private final PasswordEncoder passwordEncoder;

    public String getAccessTokenForClient(long memberId) {
        val authentication = new UserAuthentication(memberId, null, null);
        return jwtGenerator.generateToken(authentication, 12096000L);
    }

    public UserAllTokenGetResponse login(LoginRequest request, HttpServletResponse response) {
        val member = checkMemberEmpty(request);
        checkPasswordMatching(member, request.password());
        val authentication = createAuthentication(member.getId());
        val token = jwtGenerator.generateAllToken(authentication);
        tokenSaver.save(member.getId(), token.refreshToken());
        CookieUtil.addRefreshToken(response, token.refreshToken());
        return token;
    }

    public UserTokenGetResponse reissue(HttpServletRequest request) {
        val getRefreshToken = jwtProvider.getAccessTokenFromRequest(request);
        val memberId = jwtProvider.getUserFromJwt(getRefreshToken);
        val token = tokenFinder.findById(memberId);
        checkRefreshToken(getRefreshToken, token);
        val authentication = createAuthentication(memberId);
        val accessToken = jwtGenerator.generateAccessToken(authentication);
        return UserTokenGetResponse.from(accessToken);
    }

    private Member checkMemberEmpty(LoginRequest request) {
        return memberFinder.findByEmail(request.email()).orElseThrow(() -> new MemberException(INVALID_MEMBER));
    }

    private void checkRefreshToken(String getRefreshToken, Optional<Token> token) {
        if (token.isEmpty()) {
            throw new AuthException(UNAUTHORIZED_USER);
        }
        if (!token.get().refreshToken().equals(getRefreshToken)) {
            throw new AuthException(UNMATCHED_TOKEN);
        }
    }

    private void checkPasswordMatching(Member member, String password) {
        if (!passwordEncoder.matches(password,member.getPassword())) {
            throw new MemberException(UNMATCHED_PASSWORD);
        }
    }

    private Authentication createAuthentication(long memberId) {
        return new UserAuthentication(memberId, null, null);
    }
}
