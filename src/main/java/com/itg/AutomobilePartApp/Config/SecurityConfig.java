package com.itg.AutomobilePartApp.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/AutoPart/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/AutoPart/recent/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/AutoPart/get_by_code/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/User/**").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/User/block_account/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/AutoPart/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/AutoPart/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/User/enable_account/**").hasAuthority("ADMIN") // Also unblock
                        .requestMatchers(HttpMethod.DELETE, "/AutoPart/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/User/get_by_email/**", "/User/get_all/**", "/User/get_blocked_accounts/**", "/User/filter_blocked_accounts/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/Shop/shop/**").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET,  "/User/my_payment_methods/**").hasAuthority("USER")
                        .requestMatchers(HttpMethod.POST, "/User/payment/**").hasAuthority("USER")
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

}