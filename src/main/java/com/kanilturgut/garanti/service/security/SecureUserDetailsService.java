package com.kanilturgut.garanti.service.security;

import com.kanilturgut.garanti.model.User;
import com.kanilturgut.garanti.respository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        return new SecureUser(userByUsername.getUsername(), userByUsername.getPassword());
    }
}
