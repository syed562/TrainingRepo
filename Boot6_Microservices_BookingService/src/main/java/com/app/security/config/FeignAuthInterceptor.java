package com.app.security.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class FeignAuthInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {

        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes == null) {
            System.out.println("❌ Feign: No servlet request found");
            return;
        }

        HttpServletRequest request = attributes.getRequest();

      
        String jwtFromCookie = extractJwtFromCookie(request);

        if (jwtFromCookie != null) {
            System.out.println("✔ Feign: Forwarding JWT from Cookie");
            template.header("Authorization", "Bearer " + jwtFromCookie);
        } else {
          
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null) {
                System.out.println("✔ Feign: Forwarding JWT from Authorization header");
                template.header("Authorization", authHeader);
            } else {
                System.out.println("❌ Feign: No JWT token found");
            }
        }

        // --------------------------------------------
        // ✔ 3) Forward Gateway Authentication Headers
        // --------------------------------------------
        String user = request.getHeader("X-User-Name");
        String role = request.getHeader("X-User-Role");

        if (user != null) {
            System.out.println("✔ Feign: Forwarding X-User-Name → " + user);
            template.header("X-User-Name", user);
        }

        if (role != null) {
            System.out.println("✔ Feign: Forwarding X-User-Role → " + role);
            template.header("X-User-Role", role);
        }
    }

    private String extractJwtFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) return null;

        for (Cookie cookie : request.getCookies()) {
            if ("jwt".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
