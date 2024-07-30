package com.example.demo.Security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class StaticAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public StaticAuthenticationToken(Object principal) {
        super(principal, null);
    }

    public StaticAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
