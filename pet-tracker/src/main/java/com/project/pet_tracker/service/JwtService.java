package com.project.pet_tracker.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.util.Date;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class JwtService {
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;


    public String generateJwtToken(String username, String password) {

        return Jwts.builder()
                .setSubject(username)
                .setId(password)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            return !claims.getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT Token is expired!");
            return false;
        } catch (SignatureException e) {
            System.out.println("Signature does not match the secret key");
            return false;
        }
    }
}
