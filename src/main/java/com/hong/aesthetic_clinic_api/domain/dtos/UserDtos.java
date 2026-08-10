package com.hong.aesthetic_clinic_api.domain.dtos;

import com.hong.aesthetic_clinic_api.domain.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class UserDtos {
    public record  CreateUserRequest(
            UUID branchId,
            @NotBlank @Size(max = 100) String username,
            @NotBlank @Email @Size(max = 150) String email,
            @NotBlank @Size(min = 8,max = 72)String password,
            @Size(max = 150) String fullName,
            @Size(max =30) String phone,
            List<UUID> roleIds
    ){}
    public record  UpdateUserRequest(
            UUID branchedId, @Size(max = 150) String fullName,
            @Size(max = 30)String phone,UserStatus status
    ){}
    public record UserResponse(
            UUID id, UUID branchId, String username, String email,
            String fullName, String phone, UserStatus status,
            List<String>roles, Instant createdAt,Instant updatedAt
    ){}// no password field, ever
}
