package com.me.e_commerce_application.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
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

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token) {
        try {
            var claims = getClaims(token);
            return claims.getExpiration().after(new Date());  // checks, is the token expired

        }catch (JwtException e){
            return false;
        }
    }

    public String getEmailFromToken(String token){
        var claims = getClaims(token);
        return claims.getSubject();
    }

}
