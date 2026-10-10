package com.hong.aesthetic_clinic_api.Services.Impl;

import com.hong.aesthetic_clinic_api.Services.BranchService;
import com.hong.aesthetic_clinic_api.domain.BranchStatus;
import com.hong.aesthetic_clinic_api.domain.UserStatus;
import com.hong.aesthetic_clinic_api.domain.dtos.BranchesDtos;
import com.hong.aesthetic_clinic_api.domain.dtos.CreateBranchRequest;
import com.hong.aesthetic_clinic_api.domain.entities.Branch;
import com.hong.aesthetic_clinic_api.mappers.BranchMapper;
import com.hong.aesthetic_clinic_api.repositories.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    @Override
    @Transactional
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

        return branchRepository.findAll()
                .stream()
                .map(branchMapper::toResponseDto)
                .toList();
    }

    @Override
    public List<BranchesDtos.BranchResponse> listActiveBranches() {
        // The missing argument was the original bug - this query need a status filter by

        return branchRepository.findAllActiveOrderByName(BranchStatus.ACTIVE)
                .stream()
                .map(branchMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public BranchesDtos.BranchResponse updateBranch(UUID id, BranchesDtos.BranchRequest dto) {

        Branch branch = findBranchOrThrow(id);
        branchMapper.updateEntityFromDto(dto,branch);
        Branch savedData = branchRepository.save(branch);
        return branchMapper.toResponseDto(savedData);
    }

    @Override
    @Transactional
    public void deleteBranch(UUID id) {

        if(!branchRepository.existsById(id)){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"Branch not found : "+ id);
        }
        branchRepository.deleteById(id);
    }


    private Branch findBranchOrThrow(UUID id){
        return branchRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Branch not found: "+ id
                ));
    }
}
