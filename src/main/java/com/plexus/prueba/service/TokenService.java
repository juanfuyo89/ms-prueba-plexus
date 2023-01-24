package com.plexus.prueba.service;

import com.plexus.prueba.dto.TokenResponse;

public interface TokenService {
    TokenResponse validateUser(String username, String password) throws Throwable;
}
