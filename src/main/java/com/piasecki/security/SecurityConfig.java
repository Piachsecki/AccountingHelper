package com.piasecki.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {




    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests
                        (authorizeRequests -> {
                            authorizeRequests.requestMatchers("/api/invoice/uploadFile").hasRole("ADMIN");
                            authorizeRequests.requestMatchers("/admin/**").hasRole("ADMIN");
                            authorizeRequests.requestMatchers("/user/**").hasRole("USER");
                            authorizeRequests.requestMatchers("/home", "/register/**", "/login").permitAll();
                            authorizeRequests.anyRequest().authenticated();
                        })
                .formLogin(form -> {
                    form
                            .loginProcessingUrl("/login")
                            .usernameParameter("username")
                            .passwordParameter("password")
                            .successHandler((request, response, authentication) -> {
                                response.setStatus(HttpServletResponse.SC_OK);
                                response.getWriter().write("Login successful");
                            })
                            .failureHandler((request, response, exception) -> {
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                response.getWriter().write("Login failed: " + exception.getMessage());
                            });
                }).logout(logout -> {
                    logout
                            .logoutUrl("/logout")
                            .logoutSuccessHandler((request, response, authentication) -> {
                                response.setStatus(HttpServletResponse.SC_OK);
                                response.getWriter().write("Logout successful");
                            });
                })
                .build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails normalUser = User.builder()
//                .username("gc")
//                .password("$2a$12$BO/Tnmhdl72aLUslz1T4a.hF5zbBFtZH8B9s89q1ZjezdpLsgoiqa")
//                .roles("USER")
//                .build();

//        UserDetails adminUser = User.builder()
//                .username("owner")
//                .password("$2a$12$BO/Tnmhdl72aLUslz1T4a.hF5zbBFtZH8B9s89q1ZjezdpLsgoiqa")
//                .roles("ADMIN", "USER")
//                .build();

//
//        return new InMemoryUserDetailsManager(adminUser);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
