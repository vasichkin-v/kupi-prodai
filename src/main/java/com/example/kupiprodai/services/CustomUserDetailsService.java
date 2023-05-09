package com.example.kupiprodai.services;

import com.example.kupiprodai.models.User;
import com.example.kupiprodai.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byEmail = userRepository.findByEmail(username);
//        throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation"); // это дефолтная ошибка из либы
        if(byEmail == null) throw new UsernameNotFoundException("Такой пользователь не найден :(");
        return byEmail;
    }
}
