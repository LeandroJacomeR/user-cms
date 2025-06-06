package com.united.coders.cmsuser.configuration.security;

import com.united.coders.cmsuser.configuration.security.jwt.JwtEntryPoint;
import com.united.coders.cmsuser.configuration.security.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.united.coders.cmsuser.configuration.Contants.ADMIN;

@Configuration
@EnableWebSecurity
public class MainSecurity {

    @Autowired
    JwtEntryPoint jwtEntryPoint;

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtEntryPoint))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login", "/auth/register", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/actuator/health", "/person/").permitAll()
                        .requestMatchers("/user").hasRole(ADMIN)
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}