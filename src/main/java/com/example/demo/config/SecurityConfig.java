package com.example.demo.config;

import com.example.demo.jwt.AuthJwtEntryPoint;
import com.example.demo.jwt.AuthTokenFilter;
import com.example.demo.repo.AuthorityRepo;
import com.example.demo.repo.UsersRepo;
import com.example.demo.service.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private MyUserDetailsServiceImpl myUserDetailsService;

    @Autowired
    private AuthTokenFilter authTokenFilter;

    @Autowired
    private AuthJwtEntryPoint authJwtEntryPoint;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private AuthorityRepo authorityRepo;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests((requests) -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/token").permitAll()
                .requestMatchers("/registeruser").permitAll()
                .requestMatchers("/registeradmin").permitAll()
                .requestMatchers("/events").permitAll()
                .requestMatchers("/event/**").permitAll()
                .requestMatchers("/verify-user").permitAll()
                .requestMatchers("/passwordreset").permitAll()
                .requestMatchers("/check-username").permitAll()
                .requestMatchers("/search").permitAll()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest()).authenticated());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling((exception) -> exception.authenticationEntryPoint(authJwtEntryPoint));
        http.headers((headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider provider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
