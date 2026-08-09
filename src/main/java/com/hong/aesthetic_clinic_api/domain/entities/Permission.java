package com.hong.aesthetic_clinic_api.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid",updatable = false,nullable = false)
    private UUID id;

    @Column(nullable = false,unique = true,length = 100)
    private String name;

    @Column(length = 255)
    private  String description;


}
