package com.jonasson.gateway;

import com.jonasson.gateway.client.Customer;
import com.jonasson.gateway.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.config.GlobalCorsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Autowired
    UserClient userClient;

    @Bean
    public MapReactiveUserDetailsService userDetailsRepository() {

        List<UserDetails> users = new ArrayList<UserDetails>();
        Customer customers[] = userClient.getCustomers().block();

        for (Customer customer : customers) {
            if(customer.getUserName() != "admin") {
                UserDetails user = User.withDefaultPasswordEncoder()
                        .username(customer.getUserName())
                        .password(customer.getPassword())
                        .roles("USER")
                        .build();
                users.add(user);
            }
        }


        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("USER", "ADMIN")
                .build();
        users.add(admin);
        return new MapReactiveUserDetailsService( users);
    }

    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
        return http.cors()
                .and()
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
                .hasRole("USER")
                .pathMatchers("/api/v1/addcar/**")
                .hasRole("ADMIN")
                .pathMatchers("/api/v1/deletecar/**")
                .hasRole("ADMIN")
                .pathMatchers("/api/v1/updatecar/**")
                .hasRole("ADMIN")
                .pathMatchers("/api/v1/customers/**")
                .hasRole("ADMIN")
                .pathMatchers("/api/v1/singlecustomer/**")
                .hasRole("USER")
                .pathMatchers("/api/v1/getcar/**")
                .hasRole("USER")
                .pathMatchers("/api/v1/customerswithnumberoforders/**")
                .hasRole("ADMIN")
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .and()
                .build();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @RefreshScope
    public CorsWebFilter corsWebFilter(CorsConfigurationSource corsConfigurationSource) {
        return new CorsWebFilter(corsConfigurationSource);
    }

    @Bean 
    public CorsConfigurationSource corsConfigurationSource(GlobalCorsProperties globalCorsProperties) {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        globalCorsProperties.getCorsConfigurations().forEach(source::registerCorsConfiguration);
        return source;
    }
}
