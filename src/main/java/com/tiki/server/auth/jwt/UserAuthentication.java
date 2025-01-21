package com.tiki.server.auth.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserAuthentication extends UsernamePasswordAuthenticationToken {
    public UserAuthentication(
        final Object principal,
        final Object credentials,
        final Collection<? extends GrantedAuthority> authorities
    ) {
        super(principal, credentials, authorities);
    }
}
