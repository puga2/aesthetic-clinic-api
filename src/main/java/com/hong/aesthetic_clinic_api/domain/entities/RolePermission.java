package com.hong.aesthetic_clinic_api.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "role_permissions",uniqueConstraints = @UniqueConstraint(columnNames = {"role_id","permission_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolePermission {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid",updatable = false,nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "permission_id")
    private Permission permission;
}
