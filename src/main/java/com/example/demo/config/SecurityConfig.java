package com.example.demo.config;

import com.example.demo.entity.Authorities;
import com.example.demo.entity.Users;
import com.example.demo.jwt.AuthJwtEntryPoint;
import com.example.demo.jwt.AuthTokenFilter;
import com.example.demo.repo.AuthorityRepo;
import com.example.demo.repo.UsersRepo;
import com.example.demo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

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
                //.requestMatchers("/signup").permitAll()
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


    @Bean
    public CommandLineRunner initData(UserDetailsService userDetailsService) {
        return args -> {
            // Create Users
            Users user1 = new Users();
            user1.setUsername("user1");
            user1.setPassword(passwordEncoder().encode("password1"));
            user1.setEnabled(true);
            user1.setFirstname("John");
            user1.setLastname("Doe");
            user1.setEmail("user1@example.com");
            user1.setPhno(123456789);

            usersRepo.save(user1);

            // In your CommandLineRunner or a service
            Authorities userAuthority = new Authorities();
            userAuthority.setUser(user1);  // Associate user1 with this authority
            userAuthority.setAuthority("ROLE_USER");
            authorityRepo.save(userAuthority);

            // Create Users
            Users user2 = new Users();
            user2.setUsername("user2");
            user2.setPassword(passwordEncoder().encode("password2"));
            user2.setEnabled(true);
            user2.setFirstname("John");
            user2.setLastname("Doe");
            user2.setEmail("user1@example.com");
            user2.setPhno(123456789);

            usersRepo.save(user2);

            // In your CommandLineRunner or a service
            Authorities userAuthority1 = new Authorities();
            userAuthority1.setUser(user2);  // Associate user1 with this authority
            userAuthority1.setAuthority("ROLE_ADMIN");
            authorityRepo.save(userAuthority1);

        };
    }


}
