package com.hong.aesthetic_clinic_api.domain.entities;

import com.hong.aesthetic_clinic_api.domain.BranchStatus;
import com.hong.aesthetic_clinic_api.domain.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name= "branches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid",updatable = false,nullable = false)
    private UUID id;

    @Column(nullable = false,length = 150)
    private String name;

    @Column(length = 30)
    private String phone;

    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BranchStatus status;

    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;

    @Column(name ="updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        LocalDateTime now = LocalDateTime.now();
        createdAt =now;
        updatedAt = now;
        if(status==null) status = BranchStatus.ACTIVE;
    }
    @PreUpdate
    protected  void onUpdate(){
        updatedAt = LocalDateTime.now();
    }



}
