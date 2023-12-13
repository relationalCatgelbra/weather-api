package com.authdemo.auth.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@SuppressWarnings("deprecation")
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> authorizeRequests.requestMatchers("/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login"))
                .httpBasic().disable();

        http.csrf().disable();

        /*
         * http
         * .authorizeHttpRequests()
         * .requestMatchers("/**")
         * .hasRole("USER")
         * .and()
         * .formLogin();
         */
        return http.build();

    }

    @Bean
    public UserDetailsService userDetailService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}
