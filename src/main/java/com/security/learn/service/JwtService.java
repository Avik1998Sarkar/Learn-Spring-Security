package com.security.learn.service;

import com.security.learn.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private String secretKey = null;

    public String createToken(User user) {

        Map<String, Object> claims = new HashMap<>();

        return Jwts
                .builder()
                .claims()
                .add(claims)
                .subject(user.getUsername())
                .issuer("AVIK")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .and()
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey() {
        byte[] decode = Decoders.BASE64.decode(getSecretKey());
        return Keys.hmacShaKeyFor(decode);
    }

    public String getSecretKey() {
        secretKey = "9616a17bb6a10e831c8b21a8667accf59694a0fca668360ef971208e735a8b71";
        return secretKey;
    }
}
