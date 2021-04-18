package com.between.prueba.service;

import com.between.prueba.dto.TokenResponse;

public interface TokenService {
    TokenResponse validateUser(String username, String password) throws Throwable;
}
