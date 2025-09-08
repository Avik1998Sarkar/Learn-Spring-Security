package com.security.learn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // disabling csrf for testing the api in postman
                .csrf(csrf -> csrf.disable())
                // we should authenticate all requests
                .authorizeHttpRequests(
                        request -> request
                                // allowing all to access the registration endpoint
                                .requestMatchers("/register","/login").permitAll()
                                // any other request must be authenticated
                                .anyRequest().authenticated()
                )
                // using form based authentication (with default login page)
                //.formLogin(Customizer.withDefaults())
                // using basic http authentication (with popup dialog)
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails avik = User.withUsername("avik").password("{noop}sar1998kar").roles("USER").build();

        UserDetails rumki = User.withUsername("rumki").password("{noop}sar1998kar").roles("USER").build();

        return new InMemoryUserDetailsManager(avik, rumki);
    }

}
