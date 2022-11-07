package com.microservice.eurekaclient.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microservice.eurekaclient.entity.User;
import com.microservice.eurekaclient.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info(String.format("Service: loading user with email %s", email));

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new InternalAuthenticationServiceException("Incorrect user ID or password. Try again"));

        if (user.getIsActive() == null) {
            log.error("Incorrect user ID or password. Try again");
            throw new InternalAuthenticationServiceException("Incorrect user ID or password. Try again");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(String.valueOf(user.getRole()))
                .build();
    }
}
