package com.hong.aesthetic_clinic_api.mappers;


import com.hong.aesthetic_clinic_api.domain.dtos.BranchesDtos;
import com.hong.aesthetic_clinic_api.domain.dtos.CreateBranchRequest;
import com.hong.aesthetic_clinic_api.domain.entities.Branch;
import org.mapstruct.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BranchMapper {
//    @Mapping(target = "postCount",source = "posts",qualifiedByName = "calculatePostName")
//    BranchesDtos toDto(Branch branch);
//
//    Branch toEntity(CreateBranchRequest createBranchRequest);
      Branch toEntity(CreateBranchRequest dto);
      BranchesDtos.BranchResponse toResponseDto(Branch entity);

      @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
      void updateEntityFromDto(BranchesDtos.BranchRequest dto,@MappingTarget Branch entity);
}
