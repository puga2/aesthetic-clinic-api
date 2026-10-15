package com.hong.aesthetic_clinic_api.Services.Impl;

import com.hong.aesthetic_clinic_api.Services.UserService;
import com.hong.aesthetic_clinic_api.domain.UserRole;
import com.hong.aesthetic_clinic_api.domain.UserStatus;
import com.hong.aesthetic_clinic_api.domain.dtos.UserDtos;
import com.hong.aesthetic_clinic_api.domain.entities.Branch;
import com.hong.aesthetic_clinic_api.domain.entities.User;
import com.hong.aesthetic_clinic_api.repositories.BranchRepository;
import com.hong.aesthetic_clinic_api.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(UUID id) {
        return userRepository
                .findById(id)
                .orElseThrow(()->new EntityNotFoundException("User not found with id :"+id));

    }

    @Override
    @Transactional
    public UserDtos.UserResponse register(UserDtos.RegisterRequest request) {
        if(userRepository.existsByEmail(request.email())){
            throw new IllegalStateException("Email already in use");

        }
        if(userRepository.existsByUsername(request.username())){
            throw new IllegalArgumentException("Username already in use");
        }
        Branch branch = branchRepository.findById(request.branchId())
                .orElseThrow(()-> new IllegalArgumentException("Branch not found"));

        User user = User.builder()
                .username((request.username()))
                .email(request.email())
                .fullName(request.fullName())
                .phone(request.phone())
                .password(passwordEncoder.encode(request.password()))
                .role(UserRole.ADMIN)// pick your lowest-privilege role
                .status(UserStatus.ACTIVE)
                .branch(branch)
                .build();

        User saved = userRepository.save(user);

        return new  UserDtos.UserResponse(
                saved.getId(),
                saved.getBranch().getId(),
                saved.getUsername(),
                saved.getEmail(),
                saved.getFullName(),
                saved.getPhone(),
                saved.getStatus(),
                List.of(saved.getRole().name()),
                saved.getCreatedAt(),
                saved.getUpdatedAt()

        );
    }
}
