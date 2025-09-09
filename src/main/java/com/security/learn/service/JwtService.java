package com.security.learn.service;

import com.security.learn.entity.User;
import io.jsonwebtoken.Claims;
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
import java.util.function.Function;

@Service
public class JwtService {

    private String secretKey = null;

    public String extractUsername(String jwt) {
        return extractClaims(jwt, Claims::getSubject);
    }

    private <T> T extractClaims(String jwt, Function<Claims, T> claimResolver) {
        Claims claims = extractClaims(jwt);
        return claimResolver.apply(claims);

    }

    private Claims extractClaims(String jwt) {
        return Jwts
                .parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

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

    public boolean ifTokenValid(String jwt, String userName) {
        final String username = extractUsername(jwt);
        final Date expiration = extractClaims(jwt, Claims::getExpiration);
        if (username == null || !username.equals(userName) || expiration.before(new Date())) {
            return false;
        }
        return true;
    }
}
