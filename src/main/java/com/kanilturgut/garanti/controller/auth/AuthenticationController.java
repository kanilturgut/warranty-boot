package com.kanilturgut.garanti.controller.auth;

import com.kanilturgut.garanti.config.SpringSecurityConfig;
import com.kanilturgut.garanti.service.security.SecureUserDetailsService;
import com.kanilturgut.garanti.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    private final SpringSecurityConfig springSecurityConfig;
    private final SecureUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public AuthenticationController(SpringSecurityConfig springSecurityConfig, SecureUserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.springSecurityConfig = springSecurityConfig;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/authentication")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            springSecurityConfig.authenticationManagerBean().authenticate(authentication);
        } catch (AuthenticationException e) {
            throw new Exception("Incorrect username or password");
        } catch (Exception e) {
            e.printStackTrace();
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
