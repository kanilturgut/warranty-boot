package com.kanilturgut.garanti.service.security;

import com.kanilturgut.garanti.model.User;
import com.kanilturgut.garanti.respository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SecureUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public SecureUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userByUsername = userRepository.findUserByUsername(username);
        if (null == userByUsername) {
            throw new UsernameNotFoundException(username);
        }

        return new org.springframework.security.core.userdetails.User(userByUsername.getUsername(),
                userByUsername.getPassword(), Collections.emptyList());
    }
}
