package com.plexus.prueba.service;

import com.plexus.prueba.dto.TokenResponse;
import com.plexus.prueba.repository.UserRepository;
import com.plexus.prueba.security.JWTAuthorizationFilter;
import com.plexus.prueba.security.TokenGenerator;
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
    private UserRepository userRepository;
    @Autowired
    private TokenGenerator tokenGenerator;

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
