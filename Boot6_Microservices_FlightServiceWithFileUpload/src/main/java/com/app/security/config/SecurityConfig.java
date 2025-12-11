/*
package com.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.security.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // ADMIN-only endpoints
                .requestMatchers("/flight/add", "/flight/delete/**")
                .hasRole("ADMIN")

                // all other flight endpoints must be authenticated
                .requestMatchers("/flight/**")
                .authenticated()

                // allow everything else
                .anyRequest().permitAll()
            )

            // Add JWT filter
            .addFilterBefore(jwtAuthenticationFilter(),
                    UsernamePasswordAuthenticationFilter.class);

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

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	        		.requestMatchers("/uploadFlights", "/files/**").permitAll()
	            .requestMatchers("/flight/add", "/flight/delete/**")
	            .hasRole("ADMIN")
	            .requestMatchers("/flight/**").authenticated()
	            .anyRequest().permitAll()
	        )



	        // 2) Check Gateway forwarded headers (X-User-Name, X-User-Role)
	        .addFilterBefore(roleHeaderAuthenticationFilter(),
	                UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}

	

	@Bean
	public RoleHeaderAuthenticationFilter roleHeaderAuthenticationFilter() {
	    return new RoleHeaderAuthenticationFilter();
	}

}
