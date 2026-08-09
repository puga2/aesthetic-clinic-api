package com.hong.aesthetic_clinic_api.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBranchesDto {
    private UUID id;
    private UUID user_id;
    private UUID branch_id;

}
//id uuid
//user_id uuid
//branch_id uuid