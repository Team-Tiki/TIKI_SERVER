package com.tiki.server.auth.service;

import com.tiki.server.auth.dto.request.LoginRequest;
import com.tiki.server.auth.dto.response.SignInGetResponse;
import com.tiki.server.auth.dto.response.ReissueGetResponse;
import com.tiki.server.auth.exception.AuthException;
import com.tiki.server.auth.jwt.JwtProvider;
import com.tiki.server.auth.token.adapter.TokenFinder;
import com.tiki.server.auth.token.adapter.TokenSaver;
import com.tiki.server.auth.token.entity.Token;
import com.tiki.server.member.adapter.MemberFinder;
import com.tiki.server.member.entity.Member;
import com.tiki.server.member.exception.MemberException;
import com.tiki.server.auth.utils.CookieUtil;
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

    public SignInGetResponse login(LoginRequest request, HttpServletResponse response) {
        val member = checkMemberEmpty(request);
        checkPasswordMatching(member, request.password());
        val authentication = createAuthentication(member.getId());
        val accessToken = jwtGenerator.generateAccessToken(authentication);
        val refreshToken = jwtGenerator.generateRefreshToken(authentication);
        tokenSaver.save(Token.of(member.getId(), refreshToken));
        return SignInGetResponse.from(accessToken, refreshToken);
    }

    public ReissueGetResponse reissueToken(HttpServletRequest request) {
        val refreshToken = jwtProvider.getTokenFromRequest(request);
        val memberId = jwtProvider.getUserFromJwt(refreshToken);
        val token = tokenFinder.findById(memberId);
        checkRefreshToken(refreshToken, token);
        val authentication = createAuthentication(memberId);
        val accessToken = jwtGenerator.generateAccessToken(authentication);
        return ReissueGetResponse.from(accessToken);
    }

    public String getAccessTokenForClient(long memberId) {
        val authentication = createAuthentication(memberId);
        return jwtGenerator.generateToken(authentication, 12096000L);
    }

    private Member checkMemberEmpty(LoginRequest request) {
        return memberFinder.findByEmail(request.email()).orElseThrow(() -> new MemberException(INVALID_MEMBER));
    }

    private void checkRefreshToken(String getRefreshToken, Token token) {
        if (!token.refreshToken().equals(getRefreshToken)) {
            throw new AuthException(UNMATCHED_TOKEN);
        }
    }

    private void checkPasswordMatching(Member member, String password) {
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new MemberException(UNMATCHED_PASSWORD);
        }
    }

    private Authentication createAuthentication(long memberId) {
        return new UserAuthentication(memberId, null, null);
    }
}
