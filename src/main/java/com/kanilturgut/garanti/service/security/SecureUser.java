package com.kanilturgut.garanti.service.security;

import org.springframework.security.core.userdetails.User;

import java.util.Collections;

public class SecureUser extends User {

    public SecureUser(String username, String password) {
        super(username, password, Collections.emptyList());
    }
}
