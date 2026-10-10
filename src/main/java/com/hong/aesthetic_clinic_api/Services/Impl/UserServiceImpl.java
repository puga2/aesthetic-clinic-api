package com.hong.aesthetic_clinic_api.Services.Impl;

import com.hong.aesthetic_clinic_api.Services.UserService;
import com.hong.aesthetic_clinic_api.domain.entities.User;
import com.hong.aesthetic_clinic_api.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @Override
    public User getUserById(UUID id) {
        return userRepository
                .findById(id)
                .orElseThrow(()->new EntityNotFoundException("User not found with id :"+id));

    }
}
