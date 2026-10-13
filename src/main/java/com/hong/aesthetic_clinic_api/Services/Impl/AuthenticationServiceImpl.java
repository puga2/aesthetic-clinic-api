package com.hong.aesthetic_clinic_api.Services.Impl;

import com.hong.aesthetic_clinic_api.Services.AuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${jwt.secret}")// from spring not lombok
    private  String secretKey;

    private final  long jwtExpiryMs = 86400000L;

    private final AuthenticationManager authenticationManager;
    // Used to load user information from database
    private final UserDetailsService userDetailsService;

    @Override
    public UserDetails authenticate(String email, String password) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password)
        );
        return userDetailsService.loadUserByUsername(email);
    }
    /**
     * Generates a JWT token for an authenticated user.
     *
     * The token contains:
     * - Subject (username/email)
     * - Issue date
     * - Expiration date
     * - Signature
     */
    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Objects>claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+jwtExpiryMs))
                .signWith(getSigningKey() , SignatureAlgorithm.HS256)
                .compact();

    }
    private Key getSigningKey(){
        byte[] keyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
    /**
     * Validates a JWT token.
     *
     * Extracts username from token
     * and loads the corresponding user.
     */
    @Override
    public UserDetails validateToken(String token) {

        String username = extractUsername(token);

        return userDetailsService.loadUserByUsername(username);
    }
    /**
     * Extracts username (subject) from JWT token.
     *
     * Steps:
     * 1. Verify token signature using secret key
     * 2. Parse token payload (claims)
     * 3. Return the "sub" claim (username)
     */
    private String extractUsername(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        // Return subject claim (username)

        return claims.getSubject();
    }

}
