package com.sumit.quizapp.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours
    private final SecretKey SECRET_KEY;

    public JWTService() {
        // Generating a secret key using io.jsonwebtoken.Keys
        this.SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)  // Sign with the secret key directly
                .compact();
    }

    private Key getKey() {
        // Returning the secret key directly without Base64 encoding/decoding
        return this.SECRET_KEY;
    }
}
