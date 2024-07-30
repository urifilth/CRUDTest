package com.example.demo.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class StaticTokenFilter extends UsernamePasswordAuthenticationFilter {

    private final TokenProvider tokenProvider;

    public StaticTokenFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authToken = httpRequest.getHeader("Authorization");

        System.out.println("Token recibido: " + authToken);

        if (authToken != null && authToken.startsWith("Bearer ")) {
            String token = authToken.substring(7);

            System.out.println("Token extraído: " + token);

            if (tokenProvider.validateToken(token)) {
                // Token es válido, puedes establecer la autenticación
                StaticAuthenticationToken auth = new StaticAuthenticationToken(token, null, null);
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                // Token no válido
                System.out.println("Token no válido.");
            }
        } else {
            // Token no encontrado o formato incorrecto
            System.out.println("Token no encontrado o formato incorrecto.");
        }

        chain.doFilter(request, response);
    }

}
