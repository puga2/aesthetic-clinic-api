package com.hong.aesthetic_clinic_api.domain.entities;


import com.hong.aesthetic_clinic_api.domain.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
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
    @GeneratedValue
    @Column(columnDefinition = "uuid",updatable = false,nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @Column(nullable = false,unique = true,length = 100)
    private String username;

    @Column(nullable = false,unique = true,length = 150)
    private String email;

    @Column(nullable = false,length = 255)
    private String password; // Bcrypt hash only ,never plaintext;

    @Column(name = "full_name",length = 150)
    private String fullName;

    @Column(length = 30)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @Column(name = "created_at",updatable = false)
    private Instant createdAt;


    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;



    // 1. RUNS ONLY ONCE: Right before JPA runs "INSERT INTO User ..."
    @PrePersist
    protected void onCreate(){
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
        if(status == null) status = UserStatus.ACTIVE;

    }

    // 2. RUNS ON EVERY UPDATE: Right before JPA runs "UPDATE User SET ..."
    @PreUpdate void onUpdate(){
        updatedAt = Instant.now();
    }
}

