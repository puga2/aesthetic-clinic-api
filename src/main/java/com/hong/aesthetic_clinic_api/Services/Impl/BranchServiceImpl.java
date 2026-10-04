package com.hong.aesthetic_clinic_api.Services.Impl;

import com.hong.aesthetic_clinic_api.Services.BranchService;
import com.hong.aesthetic_clinic_api.domain.UserStatus;
import com.hong.aesthetic_clinic_api.domain.dtos.BranchesDtos;
import com.hong.aesthetic_clinic_api.domain.dtos.CreateBranchRequest;
import com.hong.aesthetic_clinic_api.domain.entities.Branch;
import com.hong.aesthetic_clinic_api.mappers.BranchMapper;
import com.hong.aesthetic_clinic_api.repositories.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    public BranchesDtos.BranchResponse createBranch(CreateBranchRequest dto){
        Branch branch = branchMapper.toEntity(dto);
        Branch savedResponse = branchRepository.save(branch);
        return branchMapper.toResponseDto(savedResponse);
    }

    @Override
    public BranchesDtos.BranchResponse getByIdBranch(UUID id) {
        Branch branch = findBranchOrThrow(id);
        return branchMapper.toResponseDto(branch);
    }

    @Override
    public List<BranchesDtos.BranchResponse> getAllBranch() {
        return List.of();
    }

    @Override
    public List<BranchesDtos.BranchResponse> ListActiveBranches() {
        return List.of();
    }

    @Override
    public BranchesDtos.BranchResponse UpdateBranch(UUID id, BranchesDtos.BranchRequest dto) {
        return null;
    }

    private Branch findBranchOrThrow(UUID id){
        return branchRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Branch not found: "+ id
                ));
    }
}
