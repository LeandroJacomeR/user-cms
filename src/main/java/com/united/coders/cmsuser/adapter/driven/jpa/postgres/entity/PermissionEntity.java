package com.united.coders.cmsuser.adapter.driven.jpa.postgres.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "Permission")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String action;
    private String resource;

    @ManyToMany(mappedBy = "permissions")
    private Set<RoleEntity> roles;
}
