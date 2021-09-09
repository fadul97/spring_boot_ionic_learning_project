package com.leonardofadul.springboot.ionic.learning.project.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String username){

        System.out.println("DEU PRA GEGAR O TOKEN");
        System.out.println("DEU PRA GEGAR O TOKEN");
        System.out.println("DEU PRA GEGAR O TOKEN");
        System.out.println("DEU PRA GEGAR O TOKEN!!!");

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }
}
