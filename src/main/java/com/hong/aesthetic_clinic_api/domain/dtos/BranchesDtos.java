package com.hong.aesthetic_clinic_api.domain.dtos;


import com.hong.aesthetic_clinic_api.domain.BranchStatus;
import com.hong.aesthetic_clinic_api.domain.UserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.UUID;

public class BranchesDtos {
    public record BranchRequest(
            @NotBlank @Size(max = 150) String name,
            @Size(max = 30) String phone,
            String address,
            BranchStatus status
    ){}
    public record BranchResponse(
            UUID id,
            String name,
            String phone,
            String address,
            BranchStatus status,
            Instant createdAt,
            Instant updatedAt
    ){}
//    public record BranchesDto(
//            @Size(max = 150) String name,
//            @Size(max = 30) String phone,
//            String address,
//            BranchStatus status
//    ) {}
}
//id uuid
//name varchar 150
//phone varchar 30
//address varchar
//status type
//created_at
//updated_at  timestamp 6
//package com.Hong.Blog.domain.dtos;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class PostDto {
//    private UUID id;
//    private String title;
//    private String content;
//
//    // TODO: Author -> Create AuthorDto And apply in here
//    private  AuthorDto author;
//    private CategoryDto category;
//    private Set<TagDto> tags;
//    private Integer readingTime;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
//    private PostStatus status;
//
//}