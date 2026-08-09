package com.hong.aesthetic_clinic_api.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BranchesDto {
    private UUID id;
    private String name;
    private String address;
    private Integer status;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;
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