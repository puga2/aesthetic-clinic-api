package com.hong.aesthetic_clinic_api.domain.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public class RoleDtos {
    public record RoleRequest(
            @NotBlank @Size(max = 50)String name,
            @Size(max = 255) String description
    ){}
    public record RoleResponse(
            UUID id,String name,String description,
            List<String> permissions
    ){}
    public record AssignRoleRequest(@NotNull UUID roleId){}
    public record AssignPermissionRequest(@NotNull UUID permissionId){}
}
