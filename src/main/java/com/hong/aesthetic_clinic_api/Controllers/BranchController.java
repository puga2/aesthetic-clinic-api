package com.hong.aesthetic_clinic_api.Controllers;

import com.hong.aesthetic_clinic_api.Services.BranchService;
import com.hong.aesthetic_clinic_api.domain.dtos.BranchesDtos;
import com.hong.aesthetic_clinic_api.domain.dtos.CreateBranchRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    //    @GetMapping
//    public ResponseEntity<List<BranchesDtos>> listCategories(){
//       List<BranchesDtos>  branches =
//    }
    @PostMapping
    public ResponseEntity<BranchesDtos.BranchResponse> createBranch(@Valid @RequestBody CreateBranchRequest dto){
        BranchesDtos.BranchResponse createdBranch = branchService.createBranch(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBranch);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BranchesDtos.BranchResponse> getByIdBranch(@PathVariable UUID id){
        return  ResponseEntity.ok(branchService.getByIdBranch(id));
    }
}

