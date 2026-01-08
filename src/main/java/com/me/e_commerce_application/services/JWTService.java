package com.me.e_commerce_application.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class JWTService {
    @Value("${spring.jwt.secret}")
    private String secret ;
    final long tokenExpiration = 84000; // 1 day in second
    public String generateToken(String identifier){
       return Jwts.builder()
                .subject(identifier)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*  tokenExpiration)  )
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }
}
