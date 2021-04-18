package com.between.prueba.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGenerator {

    @Value("${app.security.expiration}")
    private String expiration;

    public String getJWTToken(String username) {
        JwtBuilder builder = Jwts.builder().setId(JWTAuthorizationFilter.JWT_ID)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expiration)))
                .signWith(SignatureAlgorithm.HS512,
                        JWTAuthorizationFilter.SECRET.getBytes());
        return builder.compact();
    }

}