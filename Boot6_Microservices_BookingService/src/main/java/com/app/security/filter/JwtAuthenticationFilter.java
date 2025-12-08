package com.app.security.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final String secret = "Z3lK8s9P0q1R4u7X2b5N8m3C6v9T4r7E1u3P9x6L2q8W4s7D1c5V9b2K4m8J1";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String username = claims.getSubject();     
                String role = claims.get("role", String.class);

              
                SimpleGrantedAuthority authority =
                        new SimpleGrantedAuthority("ROLE_" + role.toUpperCase());

                
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                List.of(authority)
                        );

              
                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println("JWT Authenticated → user: " + username + " | role: " + role);

            } catch (Exception e) {
                
                System.out.println("JWT parse error: " + e.getMessage());
            }
        }
       
        filterChain.doFilter(request, response);
    }
}
