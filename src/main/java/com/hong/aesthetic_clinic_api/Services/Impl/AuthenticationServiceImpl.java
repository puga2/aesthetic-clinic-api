package com.hong.aesthetic_clinic_api.Services.Impl;

import com.hong.aesthetic_clinic_api.Services.AuthenticationService;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

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
        Map<String, Objects>Claims = new HashMap<>();

//        return Jwt.builder
//        return "";
    }

    @Override
    public UserDetails validateToken(String token) {
        return null;
    }
}
