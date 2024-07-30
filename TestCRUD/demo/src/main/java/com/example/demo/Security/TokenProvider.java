package com.example.demo.Security;

import org.springframework.stereotype.Component;

@Component
public class TokenProvider {

    private final String staticToken = "secureToken"; // Token estático

    public String getStaticToken() {
        return staticToken;
    }

    public boolean validateToken(String token) {
        return staticToken.equals(token);
    }
}
