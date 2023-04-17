package com.example.kupiprodai.configurations;

import com.example.kupiprodai.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
//@AllArgsConstructor
@Slf4j
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;


//        http
//            .authorizeRequests()
//            .antMatchers(
//                        "/",
//                                "/images/**",
//                                "/product/**",
//                                "/registration"
//            )
//                .permitAll().anyRequest().authenticated().and()
//                .formLogin().loginPage("/login").permitAll().and()
//                .logout().permitAll();

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

//    @Bean
    public DefaultSecurityFilterChain securityFilterChainOFF(HttpSecurity http) throws Exception {
//        SecurityFilterChain
        http
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/images/**",
                        "/product/**",
                        "/registration"
                )
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().permitAll();

        return http.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/products").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        log.debug("Получаем сервис сущ пользователей!");
        return customUserDetailsService;
    }
}
