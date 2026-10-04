package com.hong.aesthetic_clinic_api.domain.dtos;

import com.hong.aesthetic_clinic_api.domain.UserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBranchRequest {

    @NotBlank(message = "Branch name is required")
    @Size(min = 2, max = 50, message = "Branch name must be between {min} and {max} characters")
    @Pattern(regexp = "^[\\w\\s-]+$", message = "Branch name can only contain letters numbers spaces and hyphens")
    private String name;

    @NotBlank(message = "Phone number is required")
    @Size(max = 30, message = "Phone number must not exceed {max} characters")
    @Pattern(
            regexp = "^\\+?[0-9\\s-]{8,20}$",
            message = "Phone number must be 8-20 digits and may include +, spaces or hyphens"
    )
    private String phone;

    @NotBlank(message = "Address is required")
    @Size(min = 5, max = 255, message = "Address must be between {min} and {max} characters")
    private String address;
}

