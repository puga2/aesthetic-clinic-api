package com.hong.aesthetic_clinic_api.Services;

import com.hong.aesthetic_clinic_api.domain.dtos.UserDtos;
import com.hong.aesthetic_clinic_api.domain.entities.User;

import java.util.UUID;


public interface UserService {
    User getUserById(UUID id);
    UserDtos.UserResponse register(UserDtos.RegisterRequest request);
}

