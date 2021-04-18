package com.between.prueba.service;

import com.between.prueba.constant.Constant;
import com.between.prueba.dto.TokenResponse;
import com.between.prueba.repository.UserRepository;
import com.between.prueba.security.JWTAuthorizationFilter;
import com.between.prueba.security.TokenGenerator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@NoArgsConstructor
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenGenerator tokenGenerator;

    @Value("${app.security.expiration}")
    private String expiration;

    public TokenResponse validateUser(String username, String password) throws Throwable {
        return userRepository.validateUser(username,password)
                .map(user -> TokenResponse.builder().accessToken(tokenGenerator.getJWTToken(
                        user.getUsername())).tokenType(JWTAuthorizationFilter.PREFIX)
                        .expiresIn(expiration).build()).orElseThrow(() -> {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Authentication failed");
        });
    }

}
