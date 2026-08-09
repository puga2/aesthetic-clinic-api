package com.hong.aesthetic_clinic_api.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "user_roles",uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","role  _id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRole {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid",updatable = false,nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch =FetchType.LAZY,optional = false)
    @JoinColumn(name = "role_id")
    private Role role;
}
