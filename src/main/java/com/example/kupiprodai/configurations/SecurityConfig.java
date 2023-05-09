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
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public DefaultSecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //TODO рефакторить в след. итерац. правок
//        Варианты настройки секурности
//        https://www.baeldung.com/spring-deprecated-websecurityconfigureradapter
//        или https://www.baeldung.com/spring-security-exceptions
        http
//                .addFilter() // тут можно добавить псеводАвторизацию (subCls OncePerRequestFilter)
                .authorizeRequests()
                .antMatchers(
                        "/", "/images/**", "/page", "/registration"
                ).permitAll()
                .anyRequest()
                .authenticated()
                .and()
//                .formLogin().loginPage("/login").permitAll()
                .formLogin()
                .and()
                .logout().permitAll();

        return http.build();
    }

    class CustFilter extends OncePerRequestFilter {
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            // код псеводАвторизации
        }
    }

//    @Bean
//    @Order(0)
    SecurityFilterChain resources(HttpSecurity http) throws Exception {
        http.requestMatchers((matchers) -> matchers.antMatchers("/actuator/**", "/css/**", "/error"))
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll())
                .requestCache()
                .disable()
                .securityContext()
                .disable()
                .sessionManagement()
                .disable();
        return http.build();
    }

//    @Bean
    public UserDetailsService userDetailsService() {  //todo У меня была эта ошибка, когда я случайно настроил два UserDetailsService компонента.
        log.debug("Получаем сервис сущ пользователей!");
        return customUserDetailsService;
    }
}
