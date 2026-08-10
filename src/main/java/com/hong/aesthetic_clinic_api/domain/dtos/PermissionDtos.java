package com.hong.aesthetic_clinic_api.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class PermissionDtos {
    public record  PermissionRequest(
            @NotBlank @Size(max = 100)String name,
            @Size(max = 255) String description
    ){}
    public record  PermissionResponse(
            UUID id,String name,String description
    ){}
}
