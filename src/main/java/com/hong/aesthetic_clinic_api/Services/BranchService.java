package com.hong.aesthetic_clinic_api.Services;

import com.hong.aesthetic_clinic_api.domain.dtos.BranchesDtos;
import com.hong.aesthetic_clinic_api.domain.dtos.CreateBranchRequest;
import com.hong.aesthetic_clinic_api.domain.entities.Branch;


import java.util.List;
import java.util.UUID;

public interface BranchService {
    BranchesDtos.BranchResponse createBranch(CreateBranchRequest dto);
    BranchesDtos.BranchResponse getByIdBranch(UUID id);
    List<BranchesDtos.BranchResponse> getAllBranch();
    List<BranchesDtos.BranchResponse> listActiveBranches();
    BranchesDtos.BranchResponse updateBranch(UUID id,BranchesDtos.BranchRequest dto);
    void deleteBranch(UUID id);
}
