package com.jonasson.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public MapReactiveUserDetailsService userDetailsRepository() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("USER", "ADMIN")
                .build();
        return new MapReactiveUserDetailsService(user, admin);
    }

    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf()
                .disable()
                .authorizeExchange()

                .pathMatchers("/api/v1/ordercar/**")
                .hasRole("USER")
                .pathMatchers("/api/v1/cars/**")
                .hasRole("USER")
                .pathMatchers("/api/v1/updateorder/**")
                .hasRole("USER")
                .pathMatchers("/api/v1/myorders/**")
                .hasRole("ADMIN")
                .pathMatchers("/api/v1/addcar/**")
                .hasRole("ADMIN")
                .pathMatchers("/api/v1/deletecar/**")
                .hasRole("ADMIN")
                .pathMatchers("/api/v1/updatecar/**")
                .hasRole("ADMIN")
                .pathMatchers("/api/v1/customers/**")
                .hasRole("ADMIN")
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .and()
                .build();
    }


}
