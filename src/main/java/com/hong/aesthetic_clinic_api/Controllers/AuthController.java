package com.hong.aesthetic_clinic_api.Controllers;

import com.hong.aesthetic_clinic_api.Services.AuthenticationService;
import com.hong.aesthetic_clinic_api.Services.UserService;
import com.hong.aesthetic_clinic_api.domain.dtos.AuthResponse;
import com.hong.aesthetic_clinic_api.domain.dtos.LoginRequest;
import com.hong.aesthetic_clinic_api.domain.dtos.UserDtos;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private  final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse>login(@RequestBody LoginRequest loginRequest){
        UserDetails userDetails = authenticationService.authenticate(
                loginRequest.getEmail() ,
                loginRequest.getPassword()
        );
        String tokenValue = authenticationService.generateToken(userDetails);
        AuthResponse authResponse = AuthResponse.builder()
                .token(tokenValue)
                .expiresIn(86400)
                .build();

        return  ResponseEntity.ok(authResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDtos.UserResponse>register(
            @Valid @RequestBody UserDtos.RegisterRequest request
    ){
        UserDtos.UserResponse  response = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
