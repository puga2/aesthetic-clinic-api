package com.hong.aesthetic_clinic_api.domain.entities;

import com.hong.aesthetic_clinic_api.domain.BranchStatus;
import com.hong.aesthetic_clinic_api.domain.UserRole;
import com.hong.aesthetic_clinic_api.domain.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid",updatable = false,nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "branch_id",updatable = false)
    private Branch branch;

    @Column(nullable = true,length = 100)
    private String username;

    @Column(nullable = false,length = 150,unique = true)
    private String email;

    @Column(nullable = false,length = 255)
    private String password;

    @Column(nullable = false,length = 150)
    private String fullName;

    @Column(nullable = true,length = 30)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 50)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 50)
    private UserStatus status;


    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;


}
