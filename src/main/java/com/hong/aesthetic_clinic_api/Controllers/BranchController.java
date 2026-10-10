package com.hong.aesthetic_clinic_api.Controllers;

import com.hong.aesthetic_clinic_api.Services.BranchService;
import com.hong.aesthetic_clinic_api.domain.dtos.BranchesDtos;
import com.hong.aesthetic_clinic_api.domain.dtos.CreateBranchRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
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
    @GetMapping
    public ResponseEntity<List<BranchesDtos.BranchResponse>>getAllBranch(){
        return ResponseEntity.ok(branchService.getAllBranch());
    }
    @GetMapping("/active")
    public ResponseEntity<List<BranchesDtos.BranchResponse>>listActiveBranches(){
        return ResponseEntity.ok(branchService.listActiveBranches());
    }
    @PutMapping("/{id} ")
    public ResponseEntity<BranchesDtos.BranchResponse>
    updateBranch(@PathVariable UUID id,@Valid @RequestBody  BranchesDtos.BranchRequest dto){

        BranchesDtos.BranchResponse updated = branchService.updateBranch(id,dto);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteBranch(@PathVariable UUID id){ //
        //using string because we return it as string it could be voice if it return nothing
        branchService.deleteBranch(id);
        return ResponseEntity.ok("Branch deleted successfully");
    }




}

