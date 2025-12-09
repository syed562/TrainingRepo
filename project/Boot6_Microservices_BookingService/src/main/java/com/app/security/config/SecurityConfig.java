/*
package com.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.security.filter.RoleHeaderAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
/*
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/booking/cancel/**").hasRole("USER")
                .requestMatchers("/booking/ticket/**").authenticated()
                .requestMatchers("/booking/book/**").authenticated()
                .requestMatchers("/booking/**").authenticated()
                .anyRequest().permitAll()
            )

           
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
}*/
package com.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.security.filter.RoleHeaderAuthenticationFilter;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // Allow preflight CORS
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // USER-only endpoints
                .requestMatchers("/booking/book/**").hasRole("USER")
                .requestMatchers("/booking/ticket/**").hasRole("USER")
                .requestMatchers("/booking/cancel/**").hasRole("USER")

                // Everything under /booking requires authentication
                .requestMatchers("/booking/**").authenticated()

                // Other endpoints allowed
                .anyRequest().permitAll()
            )

            // Read role + username from Gateway headers
            .addFilterBefore(roleHeaderAuthenticationFilter(),
                    UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public RoleHeaderAuthenticationFilter roleHeaderAuthenticationFilter() {
        return new RoleHeaderAuthenticationFilter();
    }
}
